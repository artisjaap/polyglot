import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {UserLoginResponse} from '../login/response/user-login-response';

@Component({
  selector: 'pol-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.scss']
})
export class MainMenuComponent implements OnInit {
  showMenu = true;
  currentUser: UserLoginResponse;

  constructor(userService: UserService) {
    this.currentUser = userService.user;
  }

  ngOnInit() {

  }

  toggleMenu() {
    this.showMenu = !this.showMenu;
  }

}
