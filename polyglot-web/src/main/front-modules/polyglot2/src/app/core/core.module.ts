import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CoreRoutingModule} from "./core-routing.module";
import { HomeComponent } from './home/home.component';
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    CoreRoutingModule
  ],
  exports: [RouterModule]
})
export class CoreModule { }
