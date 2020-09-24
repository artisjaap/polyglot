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
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import { EffectsModule } from '@ngrx/effects';
import {EntityDataModule, EntityDefinitionService} from '@ngrx/data';
import { entityConfig } from './entity-metadata';
import {AuthenticationInterceptor} from './interceptors/authentication-interceptor';
import {ReactiveFormsModule} from '@angular/forms';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {TextToSpeechModule} from './common/text-to-speech/text-to-speech.module';
import {ServerSentEventsModule} from './common/server-sent-events/server-sent-events.module';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {TranslationLoaderService} from './common/translations/translation-loader.service';
import {Observable} from 'rxjs';
import {AppTranslations} from './common/translations/app-translations';

export function createPolyglotTranslationLoader(translationLoaderService: TranslationLoaderService){
  return new PolyglotTranslationLoader(translationLoaderService);
}

export class PolyglotTranslationLoader implements TranslateLoader {

  constructor(private translationLoaderService: TranslationLoaderService) {
  }

  public getTranslation(language: string): Observable<AppTranslations> {
    return this.translationLoaderService.forLanguage(language);
  }
}

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
        FontAwesomeModule,
        TextToSpeechModule.forRoot(),
        ServerSentEventsModule.forRoot(),
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
        StoreDevtoolsModule.instrument({maxAge: 25, logOnly: environment.production}),
        EffectsModule.forRoot([]),
        EntityDataModule.forRoot(entityConfig),
        TranslateModule.forRoot({
          loader: {
            provide: TranslateLoader,
            useFactory: createPolyglotTranslationLoader,
            deps: [TranslationLoaderService]
          },
          defaultLanguage: 'nl'
        })
        // StoreRouterConnectionModule.forRoot({
        //   stateKey: 'router',
        //   routerState: RouterState.Minimal
        // })
    ],
    providers: [
        {provide: 'API_URL', useValue: 'http://localhost:8080/'},
        {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true}
    ],
    exports: [],
    bootstrap: [AppComponent]
})
export class AppModule {


}
