import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElMoSharedModule } from 'app/shared/shared.module';
import { DestinataireComponent } from './destinataire.component';
import { DestinataireDetailComponent } from './destinataire-detail.component';
import { DestinataireUpdateComponent } from './destinataire-update.component';
import { DestinataireDeleteDialogComponent } from './destinataire-delete-dialog.component';
import { destinataireRoute } from './destinataire.route';

@NgModule({
    imports: [JhipsterElMoSharedModule, RouterModule.forChild(destinataireRoute)],
    declarations: [DestinataireComponent, DestinataireDetailComponent, DestinataireUpdateComponent, DestinataireDeleteDialogComponent],
    entryComponents: [DestinataireDeleteDialogComponent]
})
export class JhipsterElMoDestinataireModule {}
