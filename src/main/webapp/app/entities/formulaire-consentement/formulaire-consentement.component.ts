import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';
import { FormulaireConsentementService } from './formulaire-consentement.service';
import { FormulaireConsentementDeleteDialogComponent } from './formulaire-consentement-delete-dialog.component';

@Component({
    selector: 'jhi-formulaire-consentement',
    templateUrl: './formulaire-consentement.component.html'
})
export class FormulaireConsentementComponent implements OnInit, OnDestroy {
    formulaireConsentements: IFormulaireConsentement[];
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected formulaireConsentementService: FormulaireConsentementService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected modalService: NgbModal,
        protected activatedRoute: ActivatedRoute
    ) {
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
                ? this.activatedRoute.snapshot.queryParams['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.formulaireConsentementService
                .search({
                    query: this.currentSearch
                })
                .subscribe((res: HttpResponse<IFormulaireConsentement[]>) => (this.formulaireConsentements = res.body));
            return;
        }
        this.formulaireConsentementService.query().subscribe((res: HttpResponse<IFormulaireConsentement[]>) => {
            this.formulaireConsentements = res.body;
            this.currentSearch = '';
        });
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.registerChangeInFormulaireConsentements();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFormulaireConsentement) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInFormulaireConsentements() {
        this.eventSubscriber = this.eventManager.subscribe('formulaireConsentementListModification', () => this.loadAll());
    }

    delete(formulaireConsentement: IFormulaireConsentement) {
        const modalRef = this.modalService.open(FormulaireConsentementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.formulaireConsentement = formulaireConsentement;
    }
}
