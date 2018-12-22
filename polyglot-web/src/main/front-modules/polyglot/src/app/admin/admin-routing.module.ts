import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AdminDashboardComponent} from "./admin-dashboard/admin-dashboard.component";
import {DocumentComponent} from "./document/document.component";
import {ActuatorComponent} from "./actuator/actuator.component";
import {MailboxComponent} from "./mailbox/mailbox.component";

const routes: Routes = [
  {path: '', redirectTo : 'dashboard', pathMatch:'full'},
  {path: 'dashboard', component:AdminDashboardComponent, children :[
      {path:'document', component:DocumentComponent},
      {path:'actuator', component:ActuatorComponent},
      {path:'mailbox', component:MailboxComponent},

    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
