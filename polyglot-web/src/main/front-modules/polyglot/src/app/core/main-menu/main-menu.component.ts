import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'pol-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.scss']
})
export class MainMenuComponent implements OnInit {
  showMenu:boolean = true;
  constructor() { }

  ngOnInit() {
  }

  toggleMenu() {
    this.showMenu = !this.showMenu;
  }

}
