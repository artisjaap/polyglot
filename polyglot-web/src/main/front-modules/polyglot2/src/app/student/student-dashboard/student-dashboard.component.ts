import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../polyglot-common/login.service";

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.scss']
})
export class StudentDashboardComponent implements OnInit {

  constructor(public loginService:LoginService) { }

  ngOnInit() {
  }

}
