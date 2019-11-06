import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { LanguagePairCardComponent } from './language-pair-card/language-pair-card.component';
import { LanguagePairCardEmptyComponent } from './language-pair-card-empty/language-pair-card-empty.component';
import { WordPairListComponent } from './word-pair-list/word-pair-list.component';
import { LessonListComponent } from './lesson-list/lesson-list.component';
import { LessonListItemComponent } from './lesson-list/lesson-list-item/lesson-list-item.component';
import { WordSelectorComponent } from './word-selector/word-selector.component';
import { TablePagingComponent } from './table-paging/table-paging.component';


@NgModule({
  declarations: [LanguagePairCardComponent, LanguagePairCardEmptyComponent, WordPairListComponent, LessonListComponent, LessonListItemComponent, WordSelectorComponent, TablePagingComponent],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  exports: [LanguagePairCardComponent, LanguagePairCardEmptyComponent, WordPairListComponent, LessonListComponent, WordSelectorComponent],
  providers: [
    {provide: 'API_URL', useValue: 'http://localhost:8080/'},
   // {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true}
  ]
})
export class PolyglotCommonModule { }
