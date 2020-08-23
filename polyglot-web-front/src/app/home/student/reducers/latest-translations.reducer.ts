import {Action, createReducer, on} from '@ngrx/store';
import {createEntityAdapter, EntityState} from '@ngrx/entity';
import {TranslationForLessonResponse} from '../../model/translation-for-lesson-response';
import {StudentActions} from '../action-types';


export const latestTranslationsFeatureKey = 'latestTranslations';

export interface State extends EntityState<TranslationForLessonResponse> {
  loadedLanguagePairs: {};
}

export const adapter = createEntityAdapter<TranslationForLessonResponse>({});

export const initialState: State = adapter.getInitialState({
  loadedLanguagePairs: {}
});


const latestTranslationsReducer = createReducer(
  initialState,

  on(StudentActions.latestTranslationsLoaded,
    (state, action) => {
      const languagePairId = action.languagePairId;
      const latestTranslations = action.latestTranslations.map(translation => translation.id);

      return adapter.addAll(action.latestTranslations, {
        ...state,
        loadedLanguagePairs: {...state.loadedLanguagePairs,
          [languagePairId]: latestTranslations}
      });

    }),

  on(StudentActions.translationFileUploaded,
    (state, action) => {
      const languagePairId = action.loadedTranslationsFronFile.languagePairId;
      const latestTranslationIds = action.loadedTranslationsFronFile.translations.map(translation => translation.id);
      return adapter.addAll(action.loadedTranslationsFronFile.translations, {
          ...state,
          loadedLanguagePairs : {...state.loadedLanguagePairs,
            [languagePairId] :  latestTranslationIds}
        });

      // loadedLanguagePairs : {...state.loadedLanguagePairs,
      //   [languagePairId] : [...state.loadedLanguagePairs[languagePairId].filter(t => t !== action.lessonHeader.id)]}});

    })

  /*Learn:
  * when the latest words are loadedm the state in the store is updated
  * a new state is returned, containing the new translation entities in the store
  * and a new translation pair in the dictionary containing the translation keys for this translationPairId*/
  /*on(latestWordsLoaded,
    (state, action) => {
      return adapter.addAll(
        action.translations,
        {
          ...state,
          loadedLanguagePairs: {...state.loadedLanguagePairs, ...{[action.translationPairId]: action.translations.map(t => t.id)}}
        })
    }),*/
);

export function reducer(state: State | undefined, action: Action) {
  return latestTranslationsReducer(state, action);
}


export const {
  selectAll
} = adapter.getSelectors();

