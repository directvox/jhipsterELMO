import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterElMoTestModule } from '../../../test.module';
import { AttachementDemandeDeServiceDeleteDialogComponent } from 'app/entities/attachement-demande-de-service/attachement-demande-de-service-delete-dialog.component';
import { AttachementDemandeDeServiceService } from 'app/entities/attachement-demande-de-service/attachement-demande-de-service.service';

describe('Component Tests', () => {
    describe('AttachementDemandeDeService Management Delete Component', () => {
        let comp: AttachementDemandeDeServiceDeleteDialogComponent;
        let fixture: ComponentFixture<AttachementDemandeDeServiceDeleteDialogComponent>;
        let service: AttachementDemandeDeServiceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterElMoTestModule],
                declarations: [AttachementDemandeDeServiceDeleteDialogComponent]
            })
                .overrideTemplate(AttachementDemandeDeServiceDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttachementDemandeDeServiceDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttachementDemandeDeServiceService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
