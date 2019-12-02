import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';
import { FormulaireEvaluationService } from './formulaire-evaluation.service';
import { FormulaireEvaluationDeleteDialogComponent } from './formulaire-evaluation-delete-dialog.component';

@Component({
    selector: 'jhi-formulaire-evaluation',
    templateUrl: './formulaire-evaluation.component.html'
})
export class FormulaireEvaluationComponent implements OnInit, OnDestroy {
    formulaireEvaluations: IFormulaireEvaluation[];
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected formulaireEvaluationService: FormulaireEvaluationService,
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
            this.formulaireEvaluationService
                .search({
                    query: this.currentSearch
                })
                .subscribe((res: HttpResponse<IFormulaireEvaluation[]>) => (this.formulaireEvaluations = res.body));
            return;
        }
        this.formulaireEvaluationService.query().subscribe((res: HttpResponse<IFormulaireEvaluation[]>) => {
            this.formulaireEvaluations = res.body;
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
        this.registerChangeInFormulaireEvaluations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFormulaireEvaluation) {
        return item.id;
    }

    registerChangeInFormulaireEvaluations() {
        this.eventSubscriber = this.eventManager.subscribe('formulaireEvaluationListModification', () => this.loadAll());
    }

    delete(formulaireEvaluation: IFormulaireEvaluation) {
        const modalRef = this.modalService.open(FormulaireEvaluationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.formulaireEvaluation = formulaireEvaluation;
    }
}
