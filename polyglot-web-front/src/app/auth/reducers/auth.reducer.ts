import {createReducer, on} from '@ngrx/store';
import {UserLoginResponse} from '../model/user-login-response';
import {AuthActions} from '../action-types';

export const authFeatureKey = 'auth';

export interface AuthState {
  user: UserLoginResponse;
}

export const initialAuthState: AuthState = {
  user: undefined
};


export const authReducer = createReducer(
  initialAuthState,
  on(AuthActions.login, (state, action) => {
    return {
      user: action.user
    };
  }),
  on(AuthActions.logout, (state, action) => {
    return {
      user: undefined
    };
  })
);
