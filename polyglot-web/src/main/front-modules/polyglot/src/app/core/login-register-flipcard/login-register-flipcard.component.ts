import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'pol-login-register-flipcard',
  templateUrl: './login-register-flipcard.component.html',
  styleUrls: ['./login-register-flipcard.component.scss']
})
export class LoginRegisterFlipcardComponent implements OnInit {
  loginActive = true;

  constructor() { }

  ngOnInit() {
  }

  flip() {
    this.loginActive = !this.loginActive;
  }
}
