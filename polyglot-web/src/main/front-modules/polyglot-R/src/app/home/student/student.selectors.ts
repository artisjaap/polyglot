import {createFeatureSelector, createSelector} from "@ngrx/store";
import * as fromStudent from './reducers';
import {latestTranslationsFeatureKey} from "./reducers/latest-translations.reducer";
import {latestLessonsFeatureKey} from "./reducers/latest-lessons.reducer";
import {lessonFeatureKey} from "./reducers/lesson.reducer";


export const studentState =
  createFeatureSelector<fromStudent.StudentState>(fromStudent.studentFeatureKey);


/*Learn: return the dictionary of languagePairId with list of translation ids*/
export const loadedLanguagePairLatestTranslations = createSelector(
  studentState,
  state => state[latestTranslationsFeatureKey].loadedLanguagePairs
);

export const loadedLanguagePairLatestLessons  = createSelector(
  studentState,
  state => state[latestLessonsFeatureKey].loadedLanguagePairs
);

/*Learn: return list of translations for a given languagePairId
* it will lookup the translation keys and then look then up in the entities dictionary*/
export const latestTranslationsForLanguagePair = createSelector(
  loadedLanguagePairLatestTranslations,
  studentState,
  (loadedLanguages, translations, props) =>
    loadedLanguages[props.languagePairId].map(ids => translations[latestTranslationsFeatureKey].entities[ids])

);

export const latestLessonsForLanguagePair = createSelector(
  loadedLanguagePairLatestLessons,
  studentState,

  (loadedLanguages, translations, props) => {
    return loadedLanguages[props.languagePairId].map(ids => translations[latestLessonsFeatureKey].entities[ids])
  }
);

/*Learn: returns boolean if the languagePair translations are already loaded for the given languagePairId*/
export const areLatestTranslationsLoadedForLanguagePair = createSelector(
  loadedLanguagePairLatestTranslations,
  (state, props) => !!state[props.languagePairId]
);


export const areLatestLessonsLoadedForLanguagePair = createSelector(
  loadedLanguagePairLatestLessons,
  (state, props) => !!state[props.languagePairId]
);


export const isLessonLoaded = createSelector(
  studentState,
  (state, props) => !!state[lessonFeatureKey].entities[props.lessonId]
)

export const lessonWithId = createSelector(
  studentState,
  (state, props) => {

    return state[lessonFeatureKey].entities[props.lessonId];
  }
)
