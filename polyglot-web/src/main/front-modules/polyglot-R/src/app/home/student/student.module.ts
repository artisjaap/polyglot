import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";
import {StudentRoutingModule} from "./student-routing.module";
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import {LanguagePairDataService} from "../../language-pair-data-service";
import {EntityDataModule, EntityDefinitionService, EntityMetadataMap} from "@ngrx/data";
import {LanguagePair} from "./model/language-pair";
import { LanguagePairCardComponent } from './language-pair-card/language-pair-card.component';
import {ReactiveFormsModule} from "@angular/forms";
import {entityConfig} from "../../entity-metadata";
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";


const entityMetadata:EntityMetadataMap = {
  LanguagePair : {}
}

@NgModule({
  declarations: [StudentDashboardComponent, LanguagePairCardComponent],
  imports: [
    CommonModule,
    StudentRoutingModule,
    MatIconModule,
    MatCardModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
  ],
  providers: [LanguagePairDataService]
})
export class StudentModule {

  constructor(private eds: EntityDefinitionService) {
    eds.registerMetadataMap(entityMetadata);
  }

}
