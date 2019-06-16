import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'pol-main-navigation',
  templateUrl: './main-navigation.component.html',
  styleUrls: ['./main-navigation.component.scss']
})
export class MainNavigationComponent implements OnInit {
  public displayMenu = false;

  constructor() { }

  ngOnInit() {
  }

  toggleMenu() {
    this.displayMenu = !this.displayMenu;
  }

}
