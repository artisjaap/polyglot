import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DocumentManagementComponent } from './document-management/document-management.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [DocumentManagementComponent],
  exports:[SuperuserModule]
})
export class SuperuserModule { }
