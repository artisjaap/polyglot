import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {UserDashboardComponent} from "./user-dashboard/user-dashboard.component";
import {UserSettingsComponent} from "./user-settings/user-settings.component";

const routes: Routes = [
  {path: '', redirectTo : 'dashboard', pathMatch:'full'},
  {path: 'dashboard', component:UserDashboardComponent  , children :[
      {path:'user-settings', component:UserSettingsComponent},


    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {
}
