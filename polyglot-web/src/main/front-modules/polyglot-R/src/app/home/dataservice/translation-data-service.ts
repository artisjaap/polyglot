import {EntityCollectionServiceBase, EntityCollectionServiceElementsFactory} from "@ngrx/data";
import {Translation} from "../model/translation";
import {Injectable} from "@angular/core";

@Injectable()
export class TranslationDataService extends EntityCollectionServiceBase<Translation>{
  constructor(serviceElementFactory: EntityCollectionServiceElementsFactory) {
    super('Translation', serviceElementFactory);
  }
}
