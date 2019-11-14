import { Injectable } from '@angular/core';
import {
  EntityCollectionServiceBase,
  EntityCollectionServiceElementsFactory
} from '@ngrx/data';
import { LanguagePairDataService } from './language-pair-data-service';

@Injectable({ providedIn: 'root' })
export class LanguagePairDataServiceService extends EntityCollectionServiceBase<LanguagePairDataService> {
  constructor(serviceElementsFactory: EntityCollectionServiceElementsFactory) {
    super('LanguagePairDataService', serviceElementsFactory);
  }
}
