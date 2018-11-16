import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ManageComponent} from "./main/manage/manage.component";
import {LanguagePairsComponent} from "./main/manage/language-pairs/language-pairs.component";
import {TranslationManagerComponent} from "./main/manage/translation-manager/translation-manager.component";
import {PracticeComponent} from "./main/practice/practice.component";
import {TestComponent} from "./main/test/test.component";
import {RegisterSplashComponent} from "./main/public/register-splash/register-splash.component";
import {PracticeListComponent} from "./main/practice/practice-list/practice-list.component";
import {PracticeMainComponent} from "./main/practice/practice-main/practice-main.component";
import {PracticeContinuousComponent} from "./main/practice/practice-continuous/practice-continuous.component";
import {HistoryForLanguagePairComponent} from "./main/manage/history-for-language-pair/history-for-language-pair.component";
import {LessonComponent} from "./main/lesson/lessons/lesson.component";
import {LessonListComponent} from "./main/lesson/lessons/lesson-list/lesson-list.component";
import {LessonDetailComponent} from "./main/lesson/lessons/lesson-detail/lesson-detail.component";
import {LessonLanguagePairComponent} from "./main/lesson/lessons/lesson-language-pair/lesson-language-pair.component";
import {LessonTakeComponent} from "./main/lesson/lessons/lesson-take/lesson-take.component";
import {LessonPracticeComponent} from "./main/lesson/lessons/lesson-practice/lesson-practice.component";

const routes:Routes = [
  {path: "manage", component : ManageComponent, children :[
      {path: "language-pairs", component: LanguagePairsComponent},
      {path: "translations/:pairId", component: TranslationManagerComponent},
      {path: "history/:pairId", component: HistoryForLanguagePairComponent},
    ]
  },
  {path: "practice", component : PracticeMainComponent, children:[
      {path: "list", component: PracticeListComponent},
      {path: "cards/:pairId", component: PracticeComponent},
      {path: "continuous/:pairId", component: PracticeContinuousComponent},

    ]},

  {path: "lesson", component : LessonComponent, children:[
      {path: "list", component: LessonLanguagePairComponent},
      {path: "list/:pairId", component: LessonListComponent},
      {path: "list/:pairId/:lessonId", component: LessonDetailComponent},
      {path: "take/:lessonId", component: LessonTakeComponent},
      {path: "practice/:lessonId", component: LessonPracticeComponent},
    ]},
  {path: "test", component : TestComponent},
  {path: "login", component : RegisterSplashComponent},

]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
