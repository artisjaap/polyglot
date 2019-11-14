import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "../auth/login/login.component";
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "../auth/auth-guard";
import {DashboardResolver} from "./student/DashboardResolver";
import {LanguagePairDataService} from "../language-pair-data-service";


const routes: Routes = [

  {
    path: '', component: HomeComponent, loadChildren: () => import('./student/student.module').then(m => m.StudentModule), canActivate:[AuthGuard],
    resolve: {'Dashboard' : DashboardResolver}
  },


  ];

@NgModule({
  providers: [DashboardResolver, LanguagePairDataService],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {


}



