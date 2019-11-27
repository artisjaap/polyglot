import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {LessonDataService} from "../dataservice/lesson-data-service";
import {filter, finalize, first, tap} from "rxjs/operators";
import {select, Store} from "@ngrx/store";
import {areLatestTranslationsLoadedForLanguagePair, isLessonLoaded} from "./student.selectors";
import {StudentActions} from "./action-types";
import {AppState} from "../../reducers";

@Injectable()
export class LessonDetailResolver implements Resolve<boolean> {
  lessonIsLoading: boolean = false;

  constructor(private store: Store<AppState>) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    let lessonId = route.params['lessonId'];

    return this.store
      .pipe(
        select(isLessonLoaded, {lessonId: lessonId}),
        tap(lessonLoaded => {
          if (!this.lessonIsLoading && !lessonLoaded) {
            this.lessonIsLoading = true;
            this.store.dispatch(StudentActions.loadLesson({lessonId}))
          }
        }),
        filter(latestTranslationsLoaded => latestTranslationsLoaded),
        first(),
        finalize(() => this.lessonIsLoading = false)
      );



  }

}

