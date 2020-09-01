import {ModuleWithProviders, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {AuthRoutingModule} from './auth-routing.module';
import { StoreModule } from '@ngrx/store';
import * as fromAuth from './reducers/auth.reducer';
import {AuthService} from './auth.service';
import {ReactiveFormsModule} from '@angular/forms';
import {AuthGuard} from './auth-guard';
import {EffectsModule} from '@ngrx/effects';
import {AuthEffects} from './reducers/auth.effects';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';



@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AuthRoutingModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    StoreModule.forFeature(fromAuth.authFeatureKey, fromAuth.authReducer),
    EffectsModule.forFeature([AuthEffects])
  ]

})
export class AuthModule {

  static forRoot(): ModuleWithProviders<AuthModule> {
    return {
        ngModule: AuthModule,
        providers: [AuthService, AuthGuard]
    };
}
}
