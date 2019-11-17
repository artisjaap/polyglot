import {EntityCollectionServiceBase, EntityCollectionServiceElementsFactory} from "@ngrx/data";
import {Lesson} from "../model/lesson";
import {Injectable} from "@angular/core";
import {LessonHeader} from "../model/lesson-header";

@Injectable()
export class LessonDataService extends EntityCollectionServiceBase<LessonHeader>{

  constructor(serviceElementFactory: EntityCollectionServiceElementsFactory) {
    super('Lesson', serviceElementFactory);
  }
}
