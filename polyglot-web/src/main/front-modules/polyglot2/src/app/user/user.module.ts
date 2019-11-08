import {ModuleWithProviders, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {UserRoutingModule} from "./user-routing.module";
import { StoreModule } from '@ngrx/store';
import * as fromAuth from './reducers';
import {authReducer} from "./reducers";
import {LoginService} from "./login.service";
import {AuthGuard} from "./auth-guard";



@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    StoreModule.forFeature(fromAuth.authFeatureKey, authReducer)
  ]
})
export class UserModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: UserModule,
      providers: [
        LoginService,
        AuthGuard
      ]
    }
  }
}
