import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConsentement } from 'app/shared/model/consentement.model';
import { ConsentementService } from './consentement.service';
import { ConsentementDeleteDialogComponent } from './consentement-delete-dialog.component';

@Component({
    selector: 'jhi-consentement',
    templateUrl: './consentement.component.html'
})
export class ConsentementComponent implements OnInit, OnDestroy {
    consentements: IConsentement[];
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected consentementService: ConsentementService,
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
            this.consentementService
                .search({
                    query: this.currentSearch
                })
                .subscribe((res: HttpResponse<IConsentement[]>) => (this.consentements = res.body));
            return;
        }
        this.consentementService.query().subscribe((res: HttpResponse<IConsentement[]>) => {
            this.consentements = res.body;
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
        this.registerChangeInConsentements();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IConsentement) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInConsentements() {
        this.eventSubscriber = this.eventManager.subscribe('consentementListModification', () => this.loadAll());
    }

    delete(consentement: IConsentement) {
        const modalRef = this.modalService.open(ConsentementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.consentement = consentement;
    }
}
