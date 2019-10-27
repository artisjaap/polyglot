import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {AdminComponent} from "./admin/admin.component";
import {AdminDashboardComponent} from "./admin-dashboard/admin-dashboard.component";


const routes: Routes = [

  {
    path: '', component: AdminComponent, children: [
      {
        path: 'dashboard', component: AdminDashboardComponent
      }]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],

})
export class AdminRoutingModule {
}
