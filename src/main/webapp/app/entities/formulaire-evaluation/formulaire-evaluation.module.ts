import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElMoSharedModule } from 'app/shared/shared.module';
import { FormulaireEvaluationComponent } from './formulaire-evaluation.component';
import { FormulaireEvaluationDetailComponent } from './formulaire-evaluation-detail.component';
import { FormulaireEvaluationUpdateComponent } from './formulaire-evaluation-update.component';
import { FormulaireEvaluationDeleteDialogComponent } from './formulaire-evaluation-delete-dialog.component';
import { formulaireEvaluationRoute } from './formulaire-evaluation.route';

@NgModule({
    imports: [JhipsterElMoSharedModule, RouterModule.forChild(formulaireEvaluationRoute)],
    declarations: [
        FormulaireEvaluationComponent,
        FormulaireEvaluationDetailComponent,
        FormulaireEvaluationUpdateComponent,
        FormulaireEvaluationDeleteDialogComponent
    ],
    entryComponents: [FormulaireEvaluationDeleteDialogComponent]
})
export class JhipsterElMoFormulaireEvaluationModule {}
