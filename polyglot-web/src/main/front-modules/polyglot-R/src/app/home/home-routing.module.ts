import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "../auth/login/login.component";
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "../auth/auth-guard";
import {DashboardResolver} from "./student/DashboardResolver";
import {LanguagePairDataService} from "./dataservice/language-pair-data-service";
import {LanguagePairDetailComponent} from "./student/language-pair-detail/language-pair-detail.component";


const routes: Routes = [

  {
    path: '', component: HomeComponent, loadChildren: () => import('./student/student.module').then(m => m.StudentModule), canActivate:[AuthGuard]
  },


  ];

@NgModule({
  providers: [LanguagePairDataService],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {


}



