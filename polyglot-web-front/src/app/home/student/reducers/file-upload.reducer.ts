import { Action, createReducer, on } from '@ngrx/store';


export const fileUploadFeatureKey = 'fileUpload';

export interface State {

}

export const initialState: State = {

};

const fileUploadReducer = createReducer(
  initialState,

);

export function reducer(state: State | undefined, action: Action) {
  return fileUploadReducer(state, action);
}
