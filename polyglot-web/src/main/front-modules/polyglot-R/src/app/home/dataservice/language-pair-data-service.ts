import {Injectable} from "@angular/core";
import {EntityCollectionServiceBase, EntityCollectionServiceElementsFactory} from "@ngrx/data";
import {LanguagePair} from "../model/language-pair";

@Injectable()
export class LanguagePairDataService extends EntityCollectionServiceBase<LanguagePair>{

  constructor(serviceElementFactory: EntityCollectionServiceElementsFactory) {
    super('LanguagePair', serviceElementFactory);
  }
}