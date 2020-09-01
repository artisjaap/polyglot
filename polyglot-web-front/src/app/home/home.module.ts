import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {StudentModule} from './student/student.module';
import { HomeComponent } from './home/home.component';
import {HomeRoutingModule} from './home-routing.module';
import {TranslationService} from './services/translation-service';
import {LessonHeaderService} from './services/lesson-header.service';
import {LessonService} from './services/lesson-service';
import {LanguagePairService} from './services/language-pair.service';
import {UploadService} from './services/upload.service';
import {PracticeAnswerService} from './services/practice-answer.service';
import {JournalService} from './services/journal.service';



@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule
  ],
  providers: [
    TranslationService,
    LessonHeaderService,
    LessonService,
    LanguagePairService,
    UploadService,
    PracticeAnswerService,
    JournalService
  ]
})
export class HomeModule {

  static forRoot(): ModuleWithProviders<HomeModule> {
    return {
        ngModule: HomeModule,
        providers: []
    };
}
}
