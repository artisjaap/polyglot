import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentComponent } from './student/student.component';
import {StudentRoutingModule} from "./student-routing.module";
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { LanguagesComponent } from './languages/languages.component';
import { LanguageDashboardComponent } from './language-dashboard/language-dashboard.component';
import { LanguageTrainComponent } from './language-train/language-train.component';
import { LanguageLessonComponent } from './language-lesson/language-lesson.component';
import {PolyglotCommonModule} from "../polyglot-common/polyglot-common.module";
import { LanguageLessonDetailComponent } from './language-lesson-detail/language-lesson-detail.component';



@NgModule({
  declarations: [StudentComponent, StudentDashboardComponent, LanguagesComponent, LanguageDashboardComponent, LanguageTrainComponent, LanguageLessonComponent, LanguageLessonDetailComponent],
  imports: [
    CommonModule,
    StudentRoutingModule,
    PolyglotCommonModule
  ]
})
export class StudentModule { }
