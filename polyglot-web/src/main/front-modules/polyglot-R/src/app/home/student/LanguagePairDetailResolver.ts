import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {merge, Observable, of} from "rxjs";
import {LanguagePairDataService} from "../dataservice/language-pair-data-service";
import {QueryParams} from "@ngrx/data";
import {concatMap, filter, first, flatMap, tap} from "rxjs/operators";
import {LessonDataService} from "../dataservice/lesson-data-service";
import {TranslationDataService} from "../dataservice/translation-data-service";

@Injectable()
export class LanguagePairDetailResolver implements Resolve<boolean> {
  constructor(private lessonService:LessonDataService,
              private translationService:TranslationDataService){

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {

    // return of(true);
    //
     let lessons$ = this.lessonService.loaded$.pipe(
      tap(loaded => {
        if (!loaded) {
          this.lessonService.getAll();
        }
      }),
      tap(a=> console.log("tap", a)),
      filter(loaded => !!loaded),
      first()
    );

     let translation$ = this.translationService.loaded$.pipe(
       tap(loaded => {
         if (!loaded) {
           this.translationService.getAll();
         }
       }),
       tap(a=> console.log("tap", a)),
       filter(loaded => !!loaded),
       first()
     );

    return merge(translation$, lessons$);;
  }

}
