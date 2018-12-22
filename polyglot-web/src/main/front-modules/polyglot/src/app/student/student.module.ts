import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import {StudentRoutingModule} from "./student-routing.module";
import { ManageLanguagesComponent } from './manage-languages/manage-languages.component';
import { ManageWordsForLanguageComponent } from './manage-words-for-language/manage-words-for-language.component';
import { PracticeWordsComponent } from './practice-words/practice-words.component';
import { TakeLessonComponent } from './take-lesson/take-lesson.component';

@NgModule({
  declarations: [StudentDashboardComponent, ManageLanguagesComponent, ManageWordsForLanguageComponent, PracticeWordsComponent, TakeLessonComponent],
  imports: [
    CommonModule,
    StudentRoutingModule
  ]
})
export class StudentModule { }
