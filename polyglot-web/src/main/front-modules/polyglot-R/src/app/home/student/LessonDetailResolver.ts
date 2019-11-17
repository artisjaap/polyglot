import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {LessonDataService} from "../dataservice/lesson-data-service";
import {filter, first, tap} from "rxjs/operators";

@Injectable()
export class LessonDetailResolver implements Resolve<boolean> {

  constructor(private lessonDataService: LessonDataService) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    let lessonId = route.params['lessonId'];



    return this.lessonDataService.loaded$
      .pipe(
        tap(loaded => {
          if (!loaded) {
            this.lessonDataService.getByKey(lessonId);
          }
        }),
        filter(loaded => !!loaded),
        first()
      )

  }

}

