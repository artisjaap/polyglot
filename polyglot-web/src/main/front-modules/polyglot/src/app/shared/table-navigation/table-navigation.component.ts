import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {PageNavigation} from "../../core/paging/page-navigation";

@Component({
  selector: 'pol-table-navigation',
  templateUrl: './table-navigation.component.html',
  styleUrls: ['./table-navigation.component.scss']
})
export class TableNavigationComponent implements OnInit {



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
