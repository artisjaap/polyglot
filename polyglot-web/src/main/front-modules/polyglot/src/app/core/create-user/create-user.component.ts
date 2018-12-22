import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";

@Component({
  selector: 'pol-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  constructor(private userService:UserService) { }

  ngOnInit() {
  }

  createAccount(username:HTMLInputElement, password:HTMLInputElement, usertype:HTMLInputElement, email:HTMLInputElement){
    this.userService.registerUser({username:username.value,
    password:password.value,
    type:usertype.value,
    email:email.value})
  }
}
