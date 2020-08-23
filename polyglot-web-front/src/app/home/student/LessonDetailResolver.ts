import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {select, Store} from '@ngrx/store';
import {AppState} from '../../reducers';
import {isLessonLoaded} from './student.selectors';

import {filter, finalize, first, tap} from 'rxjs/operators';
import {StudentActions} from './action-types';

@Injectable()
export class LessonDetailResolver implements Resolve<boolean> {
  lessonIsLoading = false;

  constructor(private store: Store<AppState>) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    const lessonId = route.params.lessonId;

    return this.store
      .pipe(
        select(isLessonLoaded, {lessonId}),
        tap(lessonLoaded => {
          if (!lessonLoaded && !this.lessonIsLoading) {
            this.lessonIsLoading = true;
            this.store.dispatch(StudentActions.loadLesson({lessonId}));
          }
        }),
        filter( lessonLoaded => lessonLoaded),
        first(),
        finalize(() => this.lessonIsLoading = false)
    );
  }

}

