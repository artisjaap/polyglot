import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdminRoutingModule} from "./admin-routing.module";
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { DocumentComponent } from './document/document.component';
import { ActuatorComponent } from './actuator/actuator.component';
import { MailboxComponent } from './mailbox/mailbox.component';

@NgModule({
  declarations: [AdminDashboardComponent, DocumentComponent, ActuatorComponent, MailboxComponent],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
