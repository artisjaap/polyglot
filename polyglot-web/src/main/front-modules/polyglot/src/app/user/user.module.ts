import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import {UserRoutingModule} from "./user-routing.module";
import { UserSettingsComponent } from './user-settings/user-settings.component';

@NgModule({
  declarations: [UserDashboardComponent, UserSettingsComponent],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
