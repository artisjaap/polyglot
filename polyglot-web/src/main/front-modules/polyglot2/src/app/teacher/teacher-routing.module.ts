import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {TeacherComponent} from "./teacher/teacher.component";
import {TeacherDashboardComponent} from "./teacher-dashboard/teacher-dashboard.component";


const routes: Routes = [

  {
    path: '', component: TeacherComponent, children: [
      {
        path: 'dashboard', component: TeacherDashboardComponent
      }]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],

})
export class TeacherRoutingModule {
}


