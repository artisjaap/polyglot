import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StudentDashboardComponent} from './student-dashboard/student-dashboard.component';
import {StudentMainComponent} from './student-main/student-main.component';
import {DashboardResolver} from './DashboardResolver';
import {LanguagePairDetailComponent} from './language-pair-detail/language-pair-detail.component';
import {LanguagePairDetailResolver} from './LanguagePairDetailResolver';
import {LessonDetailResolver} from './LessonDetailResolver';
import {LessonDetailComponent} from './lesson-detail/lesson-detail.component';
import {PracticeLessonComponent} from './practice-lesson/practice-lesson.component';
import {PracticeLessonMultipleChoiceComponent} from './practice-lesson-multiple-choice/practice-lesson-multiple-choice.component';


const routes: Routes = [
  {
    path: 'student',
    component: StudentMainComponent,

    children: [
      {
        path: 'dashboard', component: StudentDashboardComponent,
        resolve: {Dashboard: DashboardResolver}
      },
      {
        path: 'language-pair/:languagePairId', component: LanguagePairDetailComponent,
        resolve: {LanguagePairDetail: LanguagePairDetailResolver}
      }
      ,
      {
        path: 'lesson/:lessonId', component: LessonDetailComponent,
        resolve: {LessonDetail: LessonDetailResolver}
      },
      {
        path: 'lesson/:lessonId/practice', component: PracticeLessonComponent,
        resolve: {LessonDetail: LessonDetailResolver}
      },
      {
        path: 'lesson/:lessonId/practice/multiple-choice', component: PracticeLessonMultipleChoiceComponent,
        resolve: {LessonDetail: LessonDetailResolver}
      }
    ]
  },
];


@NgModule({
  providers: [DashboardResolver, LanguagePairDetailResolver, LessonDetailResolver],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StudentRoutingModule {


}
