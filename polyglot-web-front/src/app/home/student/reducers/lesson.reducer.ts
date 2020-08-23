import {Action, createReducer, on} from '@ngrx/store';
import {createEntityAdapter, EntityState, Update} from '@ngrx/entity';
import {StudentActions} from '../action-types';
import {LessonResponse} from '../../model/lesson-response';
import {act} from '@ngrx/effects';


export const lessonFeatureKey = 'lesson';

export interface State extends EntityState<LessonResponse> {
  // loadedLessons : string[]

}

export const adapter = createEntityAdapter<LessonResponse>({});

export const initialState: State = adapter.getInitialState({
  // loadedLessons: []
});

const lessonReducer = createReducer(
  initialState,

  on(StudentActions.lessonLoaded,
    (state, action) => {
      return adapter.addOne(action.lesson, state);
    }),

  on(StudentActions.newTranslationAdded,
    (state, action) => {
    const lessonId = action.translation.lessonId;
    return {...state,
      entities : {...state.entities,
        [lessonId] : {...state.entities[lessonId],
          translations : [...state.entities[lessonId].translations,
            action.translation]}}};
    }),

  //could be more perfomant, now, delete of a word in a lesson returns the complete lesson from the server
  //it would be faster to return jus the word deleted
  on(StudentActions.translationFromLessonDeleted,
    (state, action) => {
      const update: Update<LessonResponse> = {
        id: action.lesson.id,
        changes: action.lesson
      }
      return adapter.updateOne(update, state);
  })




  /*

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
    })*/
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
