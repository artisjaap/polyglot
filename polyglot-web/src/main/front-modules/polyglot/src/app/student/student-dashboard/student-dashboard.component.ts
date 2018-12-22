import { Component, OnInit } from '@angular/core';
import {UserService} from "../../core/services/user.service";
import {UserLoginResponse} from "../../core/login/response/user-login-response";

@Component({
  selector: 'pol-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.scss']
})
export class StudentDashboardComponent implements OnInit {

  user: UserLoginResponse;

  constructor(private userService:UserService) { }

  ngOnInit() {
    this.user = this.userService.user;
  }

}
