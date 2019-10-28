import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {CoreModule} from "./core/core.module";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {PolyglotCommonModule} from "./polyglot-common/polyglot-common.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
   // HttpClientModule,
    PolyglotCommonModule,
    CoreModule
  ],
  exports: [RouterModule],
  providers: [
 // {provide: 'API_URL', useValue: 'http://localhost:8080/'},
  // {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true}
],
  bootstrap: [AppComponent]
})
export class AppModule { }
