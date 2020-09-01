import {
  ActionReducer,
  ActionReducerMap,
  createFeatureSelector,
  createSelector, INIT,
  MetaReducer
} from '@ngrx/store';
import { environment } from '../../environments/environment';
import {AuthActions} from '../auth/action-types';

export interface AppState {

}

export const reducers: ActionReducerMap<AppState> = {


};


export function logger(reducer: ActionReducer<any>): ActionReducer<any>{
  return (state, action) => {



    if ( action != null && action.type === AuthActions.logout.type) {
      return reducer( undefined, {type: INIT});
    }

    return reducer(state, action);

  }
};

export const metaReducers: MetaReducer<AppState>[] = !environment.production ? [logger] : [];
