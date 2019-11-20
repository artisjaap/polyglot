import {Action, createReducer, on} from '@ngrx/store';
import {createEntityAdapter, EntityState} from "@ngrx/entity";
import {Lesson} from "../../model/lesson";
import {StudentActions} from "../action-types";


export const lessonFeatureKey = 'lesson';

export interface State extends EntityState<Lesson> {
  // loadedLessons : string[]

}

const adapter = createEntityAdapter<Lesson>({});

export const initialState: State = adapter.getInitialState({
  // loadedLessons: []
});

const lessonReducer = createReducer(
  initialState,

  on(StudentActions.lessonLoaded,
    (state, action) => {
      return adapter.addOne(action.lesson,
        {...state})
      // ,loadedLessons : [...state.loadedLessons, action.lesson.id] })
    }),

  on(StudentActions.removeTranslationFromLesson,
    (state, action) => {
      return adapter.updateOne(action.updatedLesson, state);
    }),

  on(StudentActions.newLessonCreated,
    (state, action) => {
      return adapter.upsertOne(action.lesson, state);
    }),

  on(StudentActions.addNewTranslation,
    (state, action) => {
      return adapter.upsertOne(action.updatedLesson, state)
    })
);

/*

DELETE_ITEM: (state, action) => ({
  ...state,
  items: state.items.filter(item => action.payload !== item),
  lastUpdated: Date.now()
})

DELETE_ITEM: (state, action) => ({
  ...state,
  items: state.items.filter(item => item !== action.payload),
  lastUpdated: Date.now()
})

*/

export function reducer(state: State | undefined, action: Action) {
  return lessonReducer(state, action);
}
