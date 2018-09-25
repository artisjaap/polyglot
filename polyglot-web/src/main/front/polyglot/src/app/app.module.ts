import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { ManageComponent } from './main/manage/manage.component';
import { PracticeComponent } from './main/practice/practice.component';
import { TestComponent } from './main/test/test.component';
import {RouterModule, Routes} from "@angular/router";

const routes:Routes = [
  {path: "manage", component : ManageComponent},
  {path: "practice", component : PracticeComponent},
  {path: "test", component : TestComponent},
  ]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    ManageComponent,
    PracticeComponent,
    TestComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
