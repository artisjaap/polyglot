import {Action, createReducer, on} from '@ngrx/store';
import {createEntityAdapter, Dictionary, EntityState} from '@ngrx/entity';
import {LessonHeaderResponse} from '../../model/lesson-header-response';
import {StudentActions} from '../action-types';


export const LessonHeaderFeatureKey = 'lessonHeader';

export interface State  extends EntityState<LessonHeaderResponse> {
  loadedLanguagePairs: {};
}

export const adapter = createEntityAdapter<LessonHeaderResponse>();

export const initialState: State = adapter.getInitialState({
  loadedLanguagePairs: {}
});

const lessonReducer = createReducer(
  initialState,

  on(StudentActions.lessonHeadersLoaded,
    (state, action) => {
      const languagePairId = action.languagePairId;
      const lessonHeaderIds = action.lessonHeaders.map(lesson => lesson.id);
      return adapter.addAll(action.lessonHeaders, {
        ...state,
        loadedLanguagePairs: {...state.loadedLanguagePairs,
        [languagePairId]: lessonHeaderIds}
      });
  }),

  on(StudentActions.lessonDeleted,
    (state, action) => {
      const loadedLanguagePairs = state.loadedLanguagePairs;
      // const languagePairId = Array.from(loadedLanguagePairs.keys())
      //   .filter(key => state.loadedLanguagePairs.get(key).indexOf(action.lessonHeader.id) >= 0)[0];
      return adapter.removeOne(action.lessonHeader.id, {
        ...state,
        // loadedLanguagePairs: {...state.loadedLanguagePairs,
        //   [languagePairId] : [...state.loadedLanguagePairs[languagePairId].filter(t => t !== action.lessonHeader.id)]}
      });
  }),

  on(StudentActions.lessonCreated,
    (state, action) => {
      const languagePairId = action.languagePairId;
      return adapter.addOne(action.lessonHeader, {
        ...state,
        loadedLanguagePairs: {...state.loadedLanguagePairs,
          [languagePairId] : [...state.loadedLanguagePairs[languagePairId], action.lessonHeader.id]}
      });
    }),

  on(StudentActions.translationFileUploaded,
  (state, action) => {

    if (action.loadedTranslationsFronFile.lessonHeaderResponse) {
      const languagePairId = action.loadedTranslationsFronFile.languagePairId;
      return adapter.upsertOne(action.loadedTranslationsFronFile.lessonHeaderResponse, {
        ...state,
        loadedLanguagePairs: {...state.loadedLanguagePairs,
          [languagePairId] : [action.loadedTranslationsFronFile.lessonHeaderResponse.id, ...state.loadedLanguagePairs[languagePairId]]}
      });
    }
    return state;
  })

  /*

   on(StudentActions.newLessonCreated,
     (state, action)=> {
       let languagePairId = action.lesson.languagePairId;
       return adapter.upsertOne({id: action.lesson.id, name:action.lesson.name},
         {...state,
         loadedLanguagePairs : {...state.loadedLanguagePairs,
           [languagePairId] : [...state.loadedLanguagePairs[languagePairId], action.lesson.id]}});
     }),

   on(StudentActions.removeLesson,
     (state, action) => {

       let languagePairId = action.languagePairId;
       return adapter.removeOne(action.lessonHeader.id, {
         ...state,
         loadedLanguagePairs : {...state.loadedLanguagePairs,
           [languagePairId] : [...state.loadedLanguagePairs[languagePairId].filter(t => t !== action.lessonHeader.id)]}});
     }),

 */
);

export function reducer(state: State | undefined, action: Action) {
  return lessonReducer(state, action);
}



