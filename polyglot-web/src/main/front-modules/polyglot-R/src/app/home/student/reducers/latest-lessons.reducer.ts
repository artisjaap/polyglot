import {Action, createReducer, on} from '@ngrx/store';
import {latestLessonsLoaded} from "../student.actions";
import {createEntityAdapter, EntityState} from "@ngrx/entity";
import {LessonHeader} from "../../model/lesson-header";
import {Translation} from "../../model/translation";
import {StudentActions} from "../action-types";
import {state} from "@angular/animations";


export const latestLessonsFeatureKey = 'latestLessons';

export interface State  extends EntityState<LessonHeader> {
  loadedLanguagePairs: {}
}

export const adapter = createEntityAdapter<LessonHeader>();

export const initialState: State = adapter.getInitialState({
  loadedLanguagePairs: {}
});

const latestLessonsReducer = createReducer(
  initialState,

  on(latestLessonsLoaded,
    (state, action) => {
      return adapter.addAll(
        action.lessons,
        {
          ...state,
          loadedLanguagePairs: {...state.loadedLanguagePairs, ...{[action.translationPairId]: action.lessons.map(t => t.id)}}
                    })
    }),

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



);

export function reducer(state: State | undefined, action: Action) {
  return latestLessonsReducer(state, action);
}



