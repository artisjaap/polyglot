import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import {StudentRoutingModule} from "./student-routing.module";
import { ManageLanguagesComponent } from './manage-languages/manage-languages.component';
import { ManageWordsForLanguageComponent } from './manage-words-for-language/manage-words-for-language.component';
import { PracticeWordsComponent } from './practice-words/practice-words.component';
import { TakeLessonComponent } from './take-lesson/take-lesson.component';
import { PracticeQuestionAnswerComponent } from './practice-words/practice-question-answer/practice-question-answer.component';
import {FormsModule} from "@angular/forms";
import {CoreModule} from "../core/core.module";
import {SharedModule} from "../shared/shared.module";
import { ManageLessonsForLanguageComponent } from './manage-lessons-for-language/manage-lessons-for-language.component';
import { PracticeLessonComponent } from './practice-lesson/practice-lesson.component';
import { ManageWordsForLessonComponent } from './manage-words-for-lesson/manage-words-for-lesson.component';
import { MyPracticedWordsComponent } from './my-practiced-words/my-practiced-words.component';

@NgModule({
  declarations: [StudentDashboardComponent, ManageLanguagesComponent, ManageWordsForLanguageComponent, PracticeWordsComponent, TakeLessonComponent, PracticeQuestionAnswerComponent, ManageLessonsForLanguageComponent, PracticeLessonComponent, ManageWordsForLessonComponent, MyPracticedWordsComponent],
  imports: [
    CommonModule,
    StudentRoutingModule,
    FormsModule,
    SharedModule
  ]
})
export class StudentModule { }
