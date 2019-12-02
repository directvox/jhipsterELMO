import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';
import { ReponseDemandeDeServiceService } from './reponse-demande-de-service.service';
import { ReponseDemandeDeServiceDeleteDialogComponent } from './reponse-demande-de-service-delete-dialog.component';

@Component({
    selector: 'jhi-reponse-demande-de-service',
    templateUrl: './reponse-demande-de-service.component.html'
})
export class ReponseDemandeDeServiceComponent implements OnInit, OnDestroy {
    reponseDemandeDeServices: IReponseDemandeDeService[];
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected reponseDemandeDeServiceService: ReponseDemandeDeServiceService,
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
            this.reponseDemandeDeServiceService
                .search({
                    query: this.currentSearch
                })
                .subscribe((res: HttpResponse<IReponseDemandeDeService[]>) => (this.reponseDemandeDeServices = res.body));
            return;
        }
        this.reponseDemandeDeServiceService.query().subscribe((res: HttpResponse<IReponseDemandeDeService[]>) => {
            this.reponseDemandeDeServices = res.body;
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
        this.registerChangeInReponseDemandeDeServices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IReponseDemandeDeService) {
        return item.id;
    }

    registerChangeInReponseDemandeDeServices() {
        this.eventSubscriber = this.eventManager.subscribe('reponseDemandeDeServiceListModification', () => this.loadAll());
    }

    delete(reponseDemandeDeService: IReponseDemandeDeService) {
        const modalRef = this.modalService.open(ReponseDemandeDeServiceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.reponseDemandeDeService = reponseDemandeDeService;
    }
}
