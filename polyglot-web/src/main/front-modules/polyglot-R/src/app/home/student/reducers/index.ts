import {
  ActionReducer,
  ActionReducerMap,
  createFeatureSelector,
  createSelector,
  MetaReducer
} from '@ngrx/store';

import * as fromLatestLessons from './latest-lessons.reducer';
import * as fromLatestTranslations from './latest-translations.reducer';

export const studentFeatureKey = 'student';

export interface StudentState {

  [fromLatestLessons.latestLessonsFeatureKey]: fromLatestLessons.State;
  [fromLatestTranslations.latestTranslationsFeatureKey]: fromLatestTranslations.State;
}

export const reducers: ActionReducerMap<StudentState> = {

  [fromLatestLessons.latestLessonsFeatureKey]: fromLatestLessons.reducer,
  [fromLatestTranslations.latestTranslationsFeatureKey]: fromLatestTranslations.reducer,
};

