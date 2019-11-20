import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {merge, Observable} from "rxjs";
import {filter, finalize, first, tap} from "rxjs/operators";
import {select, Store} from "@ngrx/store";
import {AppState} from "../../reducers";
import {StudentActions} from "./action-types";
import {areLatestLessonsLoadedForLanguagePair, areLatestTranslationsLoadedForLanguagePair} from "./student.selectors";

@Injectable()
export class LanguagePairDetailResolver implements Resolve<boolean> {

  private loadingLatestTranslations: boolean = false;
  private loadingLatestLessons: boolean = false;

  constructor(private store: Store<AppState>) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {

    /*Learn: load the languagePairId from the active route*/
    const languagePairId = route.params['languagePairId'];

    /*Learn: */
    let lessons$ = this.store
      .pipe(select(areLatestLessonsLoadedForLanguagePair, {languagePairId: languagePairId}),
        tap(lessonsLoaded => {
          if (!lessonsLoaded && !this.loadingLatestLessons) {
            this.loadingLatestLessons = true;
            this.store.dispatch(StudentActions.loadLatestLessons({languagePairId}))
          }
        }),
        filter(loaded => !!loaded),
        first(),
        finalize(() => this.loadingLatestLessons = false)
      );

    /*Learn:
    - check if latest lesson for the given language pair are loaded
    - if not loaded and we are not loading yet, set indicate to loaded and dispatch the action request for loading
       - there is an effect listening on this action. It will load all translations from the backend and then dispatch that the translations are loaded
       - then, a reducer will store the loaded translations in the redux store
       - once it is loaded, the latestTranslationsLoaded will return true so that this observable will emit true (first filter a true, comes as soon as the redux store has retrieved the data)
         than it filters the first. Finalize will always be executed when the observer is returned true.
    */
    let translation$ = this.store
      .pipe(
        select(areLatestTranslationsLoadedForLanguagePair, {languagePairId: languagePairId}),
        tap(translationsLoaded => {
          if (!this.loadingLatestTranslations && !translationsLoaded) {
            this.loadingLatestTranslations = true;
            this.store.dispatch(StudentActions.loadLatestWords({languagePairId}))
          }
        }),
        filter(latestTranslationsLoaded => latestTranslationsLoaded),
        first(),
        finalize(() => this.loadingLatestTranslations = false)
      );

    return merge(translation$, lessons$);
    ;

  }

}
