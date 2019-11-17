import {createAction, props} from "@ngrx/store";
import {Translation} from "../model/translation";
import {LessonHeader} from "../model/lesson-header";


export const loadLatestWords = createAction(
  "[LanguagePair Resolver] Load latest words",
  props<{ languagePairId: string }>()
);

export const latestWordsLoaded = createAction(
  "[LanguagePair Resolver] Latest words loaded",
  props<{ translationPairId: string, translations: Translation[] }>()
);

export const loadLatestLessons = createAction(
  "[LanguagePair Resolver] Load latest lessons",
  props<{ languagePairId: string }>()
);

export const latestLessonsLoaded = createAction(
  "[LanguagePair Resolver] Latest lessons loaded",
  props<{ translationPairId: string, lessons: LessonHeader[] }>()
);
