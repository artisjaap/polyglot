import { Action, createReducer, on } from '@ngrx/store';
import {StudentActions} from '../action-types';
import {createEntityAdapter, EntityState} from '@ngrx/entity';
import {LanguagePairResponse} from '../../model/language-pair-response';


export const languagePairFeatureKey = 'languagePair';

export interface State extends EntityState<LanguagePairResponse> {
  allLoaded: boolean;
}

export const adapter = createEntityAdapter<LanguagePairResponse>();

export const initialState: State = adapter.getInitialState({
  allLoaded: false
});

const {
  selectIds,
  selectEntities,
  selectAll,
  selectTotal,
} = adapter.getSelectors();

export const selectAllLanguagePairs = selectAll;

const languagePairReducer = createReducer(
  initialState,

  on(StudentActions.allLanguagePairsLoaded,
    (state, action) => {
      return adapter.addAll(
        action.languagePairs,
        {
          ...state,
          allLoaded: true
        });
    }),

  on(StudentActions.languagePairDeleted,
    (state, action) => {
      return adapter.removeOne(action.languagePair.id, state);
    }),

  on(StudentActions.languagePairCreated,
    (state, action) => {
    return adapter.addOne(action.languagePair, state);
    })

);

export function reducer(state: State | undefined, action: Action) {
  return languagePairReducer(state, action);
}
