import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "../home/home.component";
import {StudentDashboardComponent} from "./student-dashboard/student-dashboard.component";
import {EntityDefinitionService, EntityMetadataMap} from "@ngrx/data";



const routes: Routes = [
  {
    path: 'student',
    component: StudentDashboardComponent
  },
];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StudentRoutingModule {


}
