import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {CoreModule} from "./core/core.module";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {PolyglotCommonModule} from "./polyglot-common/polyglot-common.module";
import { StoreModule } from '@ngrx/store';
import { reducers, metaReducers } from './reducers';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
   // HttpClientModule,
    PolyglotCommonModule,
    CoreModule,
    StoreModule.forRoot(reducers, {
      metaReducers,
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true
      }
    }),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production })
  ],
  exports: [RouterModule],
  providers: [
 // {provide: 'API_URL', useValue: 'http://localhost:8080/'},
  // {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true}
],
  bootstrap: [AppComponent]
})
export class AppModule { }
