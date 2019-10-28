import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {StudentComponent} from "./student/student.component";
import {StudentDashboardComponent} from "./student-dashboard/student-dashboard.component";
import {LanguagesComponent} from "./languages/languages.component";
import {LanguageDashboardComponent} from "./language-dashboard/language-dashboard.component";
import {LanguageTrainComponent} from "./language-train/language-train.component";
import {LanguageLessonComponent} from "./language-lesson/language-lesson.component";

const routes: Routes = [

  {
    path: '', component: StudentComponent, children: [
      {path: 'dashboard', component: StudentDashboardComponent},
      {path:'languages', component: LanguagesComponent},
      {path:'languages/:pairId', component: LanguageDashboardComponent},
      {path:'languages/:pairId/train', component: LanguageTrainComponent},
      {path:'languages/:pairId/lesson', component: LanguageLessonComponent},
      ]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],

})
export class StudentRoutingModule {
}


