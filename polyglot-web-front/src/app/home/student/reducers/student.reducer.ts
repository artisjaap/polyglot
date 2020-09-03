import {
  ActionReducer,
  ActionReducerMap,
  createFeatureSelector,
  createSelector, INIT,
  MetaReducer
} from '@ngrx/store';

import * as fromLessonHeader from './lesson-header.reducer';
import * as fromLatestTranslations from './latest-translations.reducer';
import * as fromLesson from './lesson.reducer';
import * as fromLanguagePair from './language-pair.reducer';
import * as fromFileUpload from './file-upload.reducer';
import * as fromPracticeLesson from './practice-lesson.reducer';
import {environment} from '../../../../environments/environment';
import {AppState, logger} from '../../../reducers/app.reducer';
import {AuthActions} from '../../../auth/action-types';

export const studentFeatureKey = 'student';

export interface StudentState {

  [fromLessonHeader.LessonHeaderFeatureKey]: fromLessonHeader.State;
  [fromLatestTranslations.latestTranslationsFeatureKey]: fromLatestTranslations.State;
  [fromLesson.lessonFeatureKey]: fromLesson.State;
  [fromLanguagePair.languagePairFeatureKey]: fromLanguagePair.State;
  [fromFileUpload.fileUploadFeatureKey]: fromFileUpload.State;
  [fromPracticeLesson.practiceLessonFeatureKey]: fromPracticeLesson.State;



}

export const reducers: ActionReducerMap<StudentState> = {

  [fromLessonHeader.LessonHeaderFeatureKey]: fromLessonHeader.reducer,
  [fromLatestTranslations.latestTranslationsFeatureKey]: fromLatestTranslations.reducer,
  [fromLesson.lessonFeatureKey]: fromLesson.reducer,
  [fromLanguagePair.languagePairFeatureKey]: fromLanguagePair.reducer,
  [fromFileUpload.fileUploadFeatureKey]: fromFileUpload.reducer,
  [fromPracticeLesson.practiceLessonFeatureKey]: fromPracticeLesson.reducer,
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

export const selectPracticeLesson = createSelector(
  selectStudentState, (state: StudentState) => state[fromPracticeLesson.practiceLessonFeatureKey]
);


