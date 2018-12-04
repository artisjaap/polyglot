import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {TestComponent} from "../test/test.component";
import {DocumentManagementComponent} from "./document-management/document-management.component";

const routes: Routes = [
  {path: "logs", component : TestComponent},
  {path: "documents", component : DocumentManagementComponent},
  {path: "db", component : TestComponent},
  {path: "actuator", component : TestComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class SuperuserRoutingModule {

}
