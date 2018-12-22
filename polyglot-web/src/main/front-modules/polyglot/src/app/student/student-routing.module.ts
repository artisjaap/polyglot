import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {StudentDashboardComponent} from "./student-dashboard/student-dashboard.component";
import {ManageLanguagesComponent} from "./manage-languages/manage-languages.component";
import {TakeLessonComponent} from "./take-lesson/take-lesson.component";
import {PracticeWordsComponent} from "./practice-words/practice-words.component";
import {ManageWordsForLanguageComponent} from "./manage-words-for-language/manage-words-for-language.component";

const routes: Routes = [
  {path: '', redirectTo : 'dashboard', pathMatch:'full'},
  {path: 'dashboard', component:StudentDashboardComponent, children :[
      {path:'manage-languages', component:ManageLanguagesComponent},
      {path:'manage-words-for-language', component:ManageWordsForLanguageComponent},
      {path:'practice-words', component:PracticeWordsComponent},
      {path:'take-lesson', component:TakeLessonComponent},

    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule {
}
