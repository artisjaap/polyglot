import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {TeacherDashboardComponent} from "./teacher-dashboard/teacher-dashboard.component";

const routes: Routes = [
  {path: '', redirectTo : 'dashboard', pathMatch:'full'},
  {path: 'dashboard', component:TeacherDashboardComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule {
}
