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
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './main/common/components/login/login.component';
import {AuthenticationInterceptor} from "./main/common/services/interceptors/authentication-interceptor";
import { CreateUserComponent } from './main/common/components/create-user/create-user.component';
import { RegisterSplashComponent } from './main/public/register-splash/register-splash.component';



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
    LoginComponent,
    CreateUserComponent,
    RegisterSplashComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {provide: "API_URL", useValue: "http://localhost:8080/"},
    {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
