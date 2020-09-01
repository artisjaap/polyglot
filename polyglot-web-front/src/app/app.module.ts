import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StoreModule } from '@ngrx/store';
import { reducers, metaReducers } from './reducers/app.reducer';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import {AuthModule} from './auth/auth.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { EffectsModule } from '@ngrx/effects';
import { EntityDataModule } from '@ngrx/data';
import { entityConfig } from './entity-metadata';
import {AuthenticationInterceptor} from './interceptors/authentication-interceptor';
import {ReactiveFormsModule} from '@angular/forms';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    AuthModule.forRoot(),
    StoreModule.forRoot(reducers, {
      metaReducers,
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true,
        strictActionSerializability: false,
        strictStateSerializability: false
      }
    }),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production }),
    EffectsModule.forRoot([]),
    EntityDataModule.forRoot(entityConfig),
    // StoreRouterConnectionModule.forRoot({
    //   stateKey: 'router',
    //   routerState: RouterState.Minimal
    // })
  ],
  providers: [
    {provide: 'API_URL', useValue: 'http://localhost:8080/'},
    {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
