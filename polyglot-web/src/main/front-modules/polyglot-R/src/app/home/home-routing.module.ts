import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {AuthComponent} from "../auth/auth/auth.component";
import {HomeComponent} from "./home/home.component";




const routes: Routes = [

  {
    path: '', component: HomeComponent, loadChildren: () => import('./student/student.module').then(m => m.StudentModule)
  },


  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {


}



