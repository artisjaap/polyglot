import {Action, createReducer, on} from '@ngrx/store';
import {PracticeAnswerResponse} from '../../model/practice-answer-response';
import {StudentActions} from '../action-types';


export const practiceLessonFeatureKey = 'practiceLesson';

export interface State {
  previousAnswer: PracticeAnswerResponse;

}


export const initialState: State = {
  previousAnswer: undefined
};


const practiceLessonReducer = createReducer(
  initialState,
  on(StudentActions.practiceWordAnswerChecked,
    (state, action) => {
    return {...state, previousAnswer : action.practiceAnswerResponse};
    })

);



export function reducer(state: State | undefined, action: Action) {
  return practiceLessonReducer(state, action);
}

