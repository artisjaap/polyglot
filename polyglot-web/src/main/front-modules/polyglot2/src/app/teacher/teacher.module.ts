import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeacherComponent } from './teacher/teacher.component';
import {TeacherRoutingModule} from "./teacher-routing.module";
import { TeacherDashboardComponent } from './teacher-dashboard/teacher-dashboard.component';



@NgModule({
  declarations: [TeacherComponent, TeacherDashboardComponent],
  imports: [
    CommonModule,
    TeacherRoutingModule
  ]
})
export class TeacherModule { }
