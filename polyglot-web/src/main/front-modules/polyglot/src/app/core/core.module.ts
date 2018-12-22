import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CoreRoutingModule} from "./core-routing.module";
import { LoginComponent } from './login/login.component';
import {RouterModule} from "@angular/router";
import { HeaderComponent } from './header/header.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthenticationInterceptor} from "./interceptor/authentication-interceptor";
import { MainMenuComponent } from './main-menu/main-menu.component';
import { HomeComponent } from './home/home.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { LoginRegisterFlipcardComponent } from './login-register-flipcard/login-register-flipcard.component';

@NgModule({
  declarations: [LoginComponent, HeaderComponent, MainMenuComponent, HomeComponent, CreateUserComponent, LoginRegisterFlipcardComponent],
  imports: [
    CommonModule,
    CoreRoutingModule,
    HttpClientModule
  ],
  exports: [RouterModule, HeaderComponent, MainMenuComponent],
  providers: [
    {provide: "API_URL", useValue: "http://localhost:8080/"},
    {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi:true}
  ]
})
export class CoreModule { }
