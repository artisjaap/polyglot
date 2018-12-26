import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'pol-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {
  }

  onSubmit(formData:NgForm) {
    this.userService.registerUser(formData.value).subscribe(r => {
      this.router.navigate(['/home/student']);

    });
  }

  createAccount(username:HTMLInputElement, password:HTMLInputElement, usertype:HTMLInputElement, email:HTMLInputElement){
    this.userService.registerUser({username:username.value,
    password:password.value,
    type:usertype.value,
    email:email.value}) .subscribe(r => {
      this.router.navigate(['/home/student']);

    });
  }
}
