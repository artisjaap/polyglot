import {
  ActionReducer,
  ActionReducerMap,
  createFeatureSelector,
  createSelector,
  MetaReducer
} from '@ngrx/store';

import * as fromLessonHeader from './lesson-header.reducer';
import * as fromLatestTranslations from './latest-translations.reducer';
import * as fromLesson from './lesson.reducer';
import * as fromLanguagePair from './language-pair.reducer';
import * as fromFileUpload from './file-upload.reducer';

export const studentFeatureKey = 'student';

export interface StudentState {

  [fromLessonHeader.LessonHeaderFeatureKey]: fromLessonHeader.State;
  [fromLatestTranslations.latestTranslationsFeatureKey]: fromLatestTranslations.State;
  [fromLesson.lessonFeatureKey]: fromLesson.State;
  [fromLanguagePair.languagePairFeatureKey]: fromLanguagePair.State;
  [fromFileUpload.fileUploadFeatureKey]: fromFileUpload.State;



}

export const reducers: ActionReducerMap<StudentState> = {

  [fromLessonHeader.LessonHeaderFeatureKey]: fromLessonHeader.reducer,
  [fromLatestTranslations.latestTranslationsFeatureKey]: fromLatestTranslations.reducer,
  [fromLesson.lessonFeatureKey]: fromLesson.reducer,
  [fromLanguagePair.languagePairFeatureKey]: fromLanguagePair.reducer,
  [fromFileUpload.fileUploadFeatureKey]: fromFileUpload.reducer,
};

export const selectStudentState = createFeatureSelector<StudentState>(studentFeatureKey);

export const selectLessonHeader = createSelector(
  selectStudentState, (state: StudentState) => state[fromLessonHeader.LessonHeaderFeatureKey]
);

export const selectLatestTranslations = createSelector(
  selectStudentState, (state: StudentState) => state[fromLatestTranslations.latestTranslationsFeatureKey]
);

export const selectLesson = createSelector(
  selectStudentState, (state: StudentState) => state[fromLesson.lessonFeatureKey]
);

export const selectLanguagePair = createSelector(
  selectStudentState, (state: StudentState) => state[fromLanguagePair.languagePairFeatureKey]
);
