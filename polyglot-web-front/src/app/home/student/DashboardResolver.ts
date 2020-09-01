import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {Injectable} from '@angular/core';
import {filter, finalize, first, tap} from 'rxjs/operators';
import {select, Store} from '@ngrx/store';
import {allLanguagePairsLoaded} from './reducers/student.selectors';
import {StudentActions} from './action-types';
import {AppState} from '../../reducers/app.reducer';

@Injectable()
export class DashboardResolver implements Resolve<boolean> {

  private languagePairsLoading = false;

  constructor(private store: Store<AppState>) {
    console.log('DashboardResolver Constructor');
  }


  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>  {

    console.log('DashboardResolver resolver route');

    return this.store
      .pipe(
        select(allLanguagePairsLoaded),
        tap(languagePairsLoaded => {
          if (!this.languagePairsLoading && !languagePairsLoaded) {
            this.languagePairsLoading = true;
            this.store.dispatch(StudentActions.loadAllLanguagePairs());
          }
        }),
        filter(allLanguagePairsLoaded => allLanguagePairsLoaded),
        first(),
        finalize(() => this.languagePairsLoading = false)
      );


    // return this.languagePairService.loaded$.pipe(
    //   tap(loaded => {
    //     if (!loaded) {
    //       this.languagePairService.getAll();
    //     }
    //   }),
    //   filter(loaded => !!loaded),
    //   first()
    // );
  }

}
