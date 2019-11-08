import {
  ActionReducer,
  ActionReducerMap,
  createFeatureSelector, createReducer,
  createSelector,
  MetaReducer, on
} from '@ngrx/store';
import {AuthActions} from "../auth.action-types";

export const authFeatureKey = 'auth';

export interface AuthState {

}

const initialAuthState:AuthState = {
  user: undefined
}

export const reducers: ActionReducerMap<AuthState> = {

};


export const authReducer = createReducer(
  initialAuthState,
  on(AuthActions.login, (state, action) => {
    return {user : action.user}
  })
  ,
  on(AuthActions.logout, (state, action) => {
    return {user:undefined};
  })
);
