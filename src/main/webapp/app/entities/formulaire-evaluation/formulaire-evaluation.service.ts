import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';

type EntityResponseType = HttpResponse<IFormulaireEvaluation>;
type EntityArrayResponseType = HttpResponse<IFormulaireEvaluation[]>;

@Injectable({ providedIn: 'root' })
export class FormulaireEvaluationService {
    public resourceUrl = SERVER_API_URL + 'api/formulaire-evaluations';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/formulaire-evaluations';

    constructor(protected http: HttpClient) {}

    create(formulaireEvaluation: IFormulaireEvaluation): Observable<EntityResponseType> {
        return this.http.post<IFormulaireEvaluation>(this.resourceUrl, formulaireEvaluation, { observe: 'response' });
    }

    update(formulaireEvaluation: IFormulaireEvaluation): Observable<EntityResponseType> {
        return this.http.put<IFormulaireEvaluation>(this.resourceUrl, formulaireEvaluation, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFormulaireEvaluation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFormulaireEvaluation[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFormulaireEvaluation[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
