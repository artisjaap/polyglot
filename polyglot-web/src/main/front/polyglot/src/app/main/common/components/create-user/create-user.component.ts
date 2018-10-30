import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {NewUserRequest} from "../../services/request/NewUserRequest";

@Component({
  selector: 'pol-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  constructor(private authenticationService:AuthenticationService) { }

  ngOnInit() {
  }

  createNewUser(username:HTMLInputElement, password:HTMLInputElement){
    const newUser = new NewUserRequest(username.value, password.value);
    this.authenticationService.registerUser(newUser).subscribe(user => {
    });
  }

}
