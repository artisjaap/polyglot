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

      return adapter.setAll(action.latestTranslations, {
        ...state,
        loadedLanguagePairs: {...state.loadedLanguagePairs,
          [languagePairId]: latestTranslations}
      });

    }),

  on(StudentActions.translationFileUploaded,
    (state, action) => {
      const languagePairId = action.loadedTranslationsFronFile.languagePairId;
      const latestTranslationIds = action.loadedTranslationsFronFile.translations.map(translation => translation.id);
      return adapter.setAll(action.loadedTranslationsFronFile.translations, {
          ...state,
          loadedLanguagePairs : {...state.loadedLanguagePairs,
            [languagePairId] :  latestTranslationIds}
        });
    })

);

export function reducer(state: State | undefined, action: Action) {
  return latestTranslationsReducer(state, action);
}


export const {
  selectAll
} = adapter.getSelectors();

