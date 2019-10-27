import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {StudentComponent} from "./student/student.component";
import {StudentDashboardComponent} from "./student-dashboard/student-dashboard.component";

const routes: Routes = [

  {
    path: '', component: StudentComponent, children: [
      {
        path: 'dashboard', component: StudentDashboardComponent
      }]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],

})
export class StudentRoutingModule {
}
