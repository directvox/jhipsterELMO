import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IIntervenant } from 'app/shared/model/intervenant.model';
import { IntervenantService } from './intervenant.service';
import { IntervenantDeleteDialogComponent } from './intervenant-delete-dialog.component';

@Component({
    selector: 'jhi-intervenant',
    templateUrl: './intervenant.component.html'
})
export class IntervenantComponent implements OnInit, OnDestroy {
    intervenants: IIntervenant[];
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected intervenantService: IntervenantService,
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
            this.intervenantService
                .search({
                    query: this.currentSearch
                })
                .subscribe((res: HttpResponse<IIntervenant[]>) => (this.intervenants = res.body));
            return;
        }
        this.intervenantService.query().subscribe((res: HttpResponse<IIntervenant[]>) => {
            this.intervenants = res.body;
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
        this.registerChangeInIntervenants();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IIntervenant) {
        return item.id;
    }

    registerChangeInIntervenants() {
        this.eventSubscriber = this.eventManager.subscribe('intervenantListModification', () => this.loadAll());
    }

    delete(intervenant: IIntervenant) {
        const modalRef = this.modalService.open(IntervenantDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.intervenant = intervenant;
    }
}
