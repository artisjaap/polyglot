import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {PageNavigation} from "./page-navigation";

@Component({
  selector: 'pol-page-navigation',
  templateUrl: './page-navigation.component.html',
  styleUrls: ['./page-navigation.component.scss']
})
export class PageNavigationComponent implements OnInit {

  @Input("control") controls:PageNavigation<any>;
  @ViewChild("search") search: ElementRef;

  constructor() { }

  ngOnInit() {
    this.firstPage();
  }

  firstPage(){
    this.controls.goToFirstPage();
  }

  lastPage(){
    this.controls.goToLastPage();
  }

  previousPage(){
    this.controls.goToPreviousPage();
  }

  nextPage(){
    this.controls.goToNextPage();
  }

  doSearch(){
    this.controls.searchForText(this.search.nativeElement.value);
  }

}
