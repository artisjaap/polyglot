import {Action, createReducer, on} from '@ngrx/store';
import {createEntityAdapter, EntityState} from "@ngrx/entity";
import {TranslationForLessonResponse} from "../../model/translation-for-lesson-response";


export const latestTranslationsFeatureKey = 'latestTranslations';

export interface State extends EntityState<TranslationForLessonResponse> {
  loadedLanguagePairs: {}
}

export const adapter = createEntityAdapter<TranslationForLessonResponse>({});

export const initialState: State = adapter.getInitialState({
  loadedLanguagePairs: {}
});

const latestTranslationsReducer = createReducer(
  initialState,
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

