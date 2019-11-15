import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable, of} from "rxjs";
import {LanguagePairDataService} from "../dataservice/language-pair-data-service";
import {Injectable} from "@angular/core";
import {filter, first, tap} from "rxjs/operators";

@Injectable()
export class DashboardResolver implements Resolve<boolean> {
  constructor(private languagePairService: LanguagePairDataService) {

  }


  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>  {
    return this.languagePairService.loaded$.pipe(
      tap(loaded => {
        if (!loaded) {
          this.languagePairService.getAll();
        }
      }),
      filter(loaded => !!loaded),
      first()
    );
  }

}
