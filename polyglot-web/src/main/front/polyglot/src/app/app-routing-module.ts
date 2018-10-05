import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ManageComponent} from "./main/manage/manage.component";
import {LanguagePairsComponent} from "./main/manage/language-pairs/language-pairs.component";
import {TranslationManagerComponent} from "./main/manage/translation-manager/translation-manager.component";
import {PracticeComponent} from "./main/practice/practice.component";
import {TestComponent} from "./main/test/test.component";

const routes:Routes = [
  {path: "manage", component : ManageComponent, children :[
      {path: "language-pairs", component: LanguagePairsComponent},
      {path: "translations/:pairId", component: TranslationManagerComponent}]
  },
  {path: "practice", component : PracticeComponent},
  {path: "test", component : TestComponent},
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
