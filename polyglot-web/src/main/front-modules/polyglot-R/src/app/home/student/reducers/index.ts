import {
  ActionReducer,
  ActionReducerMap,
  createFeatureSelector,
  createSelector,
  MetaReducer
} from '@ngrx/store';

import * as fromLatestLessons from './latest-lessons.reducer';
import * as fromLatestTranslations from './latest-translations.reducer';
import * as fromLesson from './lesson.reducer';

export const studentFeatureKey = 'student';

export interface StudentState {

  [fromLatestLessons.latestLessonsFeatureKey]: fromLatestLessons.State;
  [fromLatestTranslations.latestTranslationsFeatureKey]: fromLatestTranslations.State;
  [fromLesson.lessonFeatureKey]: fromLesson.State;

}

export const reducers: ActionReducerMap<StudentState> = {

  [fromLatestLessons.latestLessonsFeatureKey]: fromLatestLessons.reducer,
  [fromLatestTranslations.latestTranslationsFeatureKey]: fromLatestTranslations.reducer,
  [fromLesson.lessonFeatureKey]: fromLesson.reducer,
};

