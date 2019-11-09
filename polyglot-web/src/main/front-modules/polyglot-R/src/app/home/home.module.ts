import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {StudentModule} from "./student/student.module";
import { HomeComponent } from './home/home.component';
import {HomeRoutingModule} from "./home-routing.module";



@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule
  ]
})
export class HomeModule {

  static forRoot(){
    return {
      ngModule: HomeModule,

      providers: []
    }
  }
}
