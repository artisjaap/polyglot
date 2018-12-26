import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'pol-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {
  }

  onSubmit(formData:NgForm){
    console.log("submit", formData);
  }

  login(username:HTMLInputElement, password:HTMLInputElement) {
    this.userService.authenticate(username.value, password.value)
      .subscribe(r => {
        this.router.navigate(['/student']);

      });
  }

}
