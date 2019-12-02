import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';
import { AttachementDemandeDeServiceService } from './attachement-demande-de-service.service';
import { AttachementDemandeDeServiceDeleteDialogComponent } from './attachement-demande-de-service-delete-dialog.component';

@Component({
    selector: 'jhi-attachement-demande-de-service',
    templateUrl: './attachement-demande-de-service.component.html'
})
export class AttachementDemandeDeServiceComponent implements OnInit, OnDestroy {
    attachementDemandeDeServices: IAttachementDemandeDeService[];
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected attachementDemandeDeServiceService: AttachementDemandeDeServiceService,
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
            this.attachementDemandeDeServiceService
                .search({
                    query: this.currentSearch
                })
                .subscribe((res: HttpResponse<IAttachementDemandeDeService[]>) => (this.attachementDemandeDeServices = res.body));
            return;
        }
        this.attachementDemandeDeServiceService.query().subscribe((res: HttpResponse<IAttachementDemandeDeService[]>) => {
            this.attachementDemandeDeServices = res.body;
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
        this.registerChangeInAttachementDemandeDeServices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAttachementDemandeDeService) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInAttachementDemandeDeServices() {
        this.eventSubscriber = this.eventManager.subscribe('attachementDemandeDeServiceListModification', () => this.loadAll());
    }

    delete(attachementDemandeDeService: IAttachementDemandeDeService) {
        const modalRef = this.modalService.open(AttachementDemandeDeServiceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.attachementDemandeDeService = attachementDemandeDeService;
    }
}
