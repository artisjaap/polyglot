import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {StudentDashboardComponent} from "./student-dashboard/student-dashboard.component";
import {StudentMainComponent} from "./student-main/student-main.component";
import {DashboardResolver} from "./DashboardResolver";
import {LanguagePairDetailComponent} from "./language-pair-detail/language-pair-detail.component";
import {LanguagePairDetailResolver} from "./LanguagePairDetailResolver";
import {LanguagePairDataService} from "../dataservice/language-pair-data-service";


const routes: Routes = [
  {
    path: 'student',
    component: StudentMainComponent,
    children: [{
      path: 'dashboard', component: StudentDashboardComponent,
      resolve: {'Dashboard': DashboardResolver}
    }, {
      path: 'language-pair/:languagePairId', component: LanguagePairDetailComponent,
      resolve: {'LanguagePairDetail': LanguagePairDetailResolver}
    }]
  },
];


@NgModule({
  providers: [DashboardResolver, LanguagePairDetailResolver],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StudentRoutingModule {


}
