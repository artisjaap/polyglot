import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TableNavigationComponent} from "./table-navigation/table-navigation.component";

@NgModule({
  declarations: [TableNavigationComponent],
  exports:[TableNavigationComponent],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
