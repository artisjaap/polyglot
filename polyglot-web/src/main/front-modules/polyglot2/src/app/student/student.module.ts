import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentComponent } from './student/student.component';
import {StudentRoutingModule} from "./student-routing.module";
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { LanguagesComponent } from './languages/languages.component';
import { LanguageDashboardComponent } from './language-dashboard/language-dashboard.component';
import { LanguageTrainComponent } from './language-train/language-train.component';
import { LanguageLessonComponent } from './language-lesson/language-lesson.component';



@NgModule({
  declarations: [StudentComponent, StudentDashboardComponent, LanguagesComponent, LanguageDashboardComponent, LanguageTrainComponent, LanguageLessonComponent],
  imports: [
    CommonModule,
    StudentRoutingModule
  ]
})
export class StudentModule { }
