import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDestinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from './destinataire.service';
import { DestinataireDeleteDialogComponent } from './destinataire-delete-dialog.component';

@Component({
    selector: 'jhi-destinataire',
    templateUrl: './destinataire.component.html'
})
export class DestinataireComponent implements OnInit, OnDestroy {
    destinataires: IDestinataire[];
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected destinataireService: DestinataireService,
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
            this.destinataireService
                .search({
                    query: this.currentSearch
                })
                .subscribe((res: HttpResponse<IDestinataire[]>) => (this.destinataires = res.body));
            return;
        }
        this.destinataireService.query().subscribe((res: HttpResponse<IDestinataire[]>) => {
            this.destinataires = res.body;
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
        this.registerChangeInDestinataires();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDestinataire) {
        return item.id;
    }

    registerChangeInDestinataires() {
        this.eventSubscriber = this.eventManager.subscribe('destinataireListModification', () => this.loadAll());
    }

    delete(destinataire: IDestinataire) {
        const modalRef = this.modalService.open(DestinataireDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.destinataire = destinataire;
    }
}
