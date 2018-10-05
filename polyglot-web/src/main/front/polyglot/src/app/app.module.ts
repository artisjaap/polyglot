import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { ManageComponent } from './main/manage/manage.component';
import { PracticeComponent } from './main/practice/practice.component';
import { TestComponent } from './main/test/test.component';
import {FormsModule} from "@angular/forms";
import { LanguagePairsComponent } from './main/manage/language-pairs/language-pairs.component';
import { TranslationCardComponent } from './main/common/components/translation-card/translation-card.component';
import { TranslationManagerComponent } from './main/manage/translation-manager/translation-manager.component';
import { TranslationSimpleAddComponent } from './main/manage/translation-simple-add/translation-simple-add.component';
import {AppRoutingModule} from "./app-routing-module";



@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    ManageComponent,
    PracticeComponent,
    TestComponent,
    LanguagePairsComponent,
    TranslationCardComponent,
    TranslationManagerComponent,
    TranslationSimpleAddComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
