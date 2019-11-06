import {Component, Input, OnInit} from '@angular/core';
import {IPaging} from "./ipaging";

@Component({
  selector: 'app-table-paging',
  templateUrl: './table-paging.component.html',
  styleUrls: ['./table-paging.component.scss']
})
export class TablePagingComponent implements OnInit {

  @Input()
  pagingControls: IPaging;

  constructor() { }

  ngOnInit() {
  }

  previousPage() {
    this.pagingControls.previousPage();
  }

  nextPage(){
    this.pagingControls.nextPage();
  }

  firstPage(){
    this.pagingControls.firstPage();
  }

  lastPage(){
    this.pagingControls.lastPage();
  }

}
