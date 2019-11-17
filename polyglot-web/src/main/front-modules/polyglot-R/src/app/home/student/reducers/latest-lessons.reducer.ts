import {Action, createReducer, on} from '@ngrx/store';
import {latestLessonsLoaded} from "../student.actions";
import {createEntityAdapter, EntityState} from "@ngrx/entity";
import {LessonHeader} from "../../model/lesson-header";
import {Translation} from "../../model/translation";


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
);

export function reducer(state: State | undefined, action: Action) {
  return latestLessonsReducer(state, action);
}



