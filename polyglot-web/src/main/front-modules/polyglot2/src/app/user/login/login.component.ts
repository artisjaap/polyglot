import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../polyglot-common/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private loginService:LoginService) { }

  ngOnInit() {
  }

  public setValue(){
    this.loginService.setValue("VAL");
  }

}
