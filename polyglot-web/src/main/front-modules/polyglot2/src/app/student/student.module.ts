import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentComponent } from './student/student.component';
import {StudentRoutingModule} from "./student-routing.module";
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';



@NgModule({
  declarations: [StudentComponent, StudentDashboardComponent],
  imports: [
    CommonModule,
    StudentRoutingModule
  ]
})
export class StudentModule { }
