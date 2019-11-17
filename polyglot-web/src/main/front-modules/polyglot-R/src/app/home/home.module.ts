import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {StudentModule} from "./student/student.module";
import { HomeComponent } from './home/home.component';
import {HomeRoutingModule} from "./home-routing.module";
import {LanguagePairDataService} from "./dataservice/language-pair-data-service";
import {LessonDataService} from "./dataservice/lesson-data-service";
import {TranslationDataService} from "./dataservice/translation-data-service";
import {TranslationService} from "./services/translation-service";
import {LessonService} from "./services/lesson-service";



@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule
  ],
  providers: [LanguagePairDataService, LessonDataService, TranslationDataService, TranslationService, LessonService]
})
export class HomeModule {

  static forRoot(){
    return {
      ngModule: HomeModule,

      providers: []
    }
  }
}
