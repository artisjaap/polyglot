import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  providers: [
    {provide: 'API_URL', useValue: 'http://localhost:8080/'},
   // {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true}
  ]
})
export class PolyglotCommonModule { }
