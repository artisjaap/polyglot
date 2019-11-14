import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {AuthRoutingModule} from "./auth-routing.module";
import { StoreModule } from '@ngrx/store';
import * as fromAuth from './reducers';
import {AuthService} from "./auth.service";
import {ReactiveFormsModule} from "@angular/forms";
import {authReducer} from "./reducers";
import {AuthGuard} from "./auth-guard";
import {EffectsModule} from "@ngrx/effects";
import {AuthEffects} from "./auth.effects";



@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AuthRoutingModule,
    StoreModule.forFeature(fromAuth.authFeatureKey, authReducer),
    EffectsModule.forFeature([AuthEffects])
  ]

})
export class AuthModule {

  static forRoot(){
    return {
      ngModule: AuthModule,

      providers: [AuthService,AuthGuard]
    }
  }
}
