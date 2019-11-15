import { Component, OnInit } from '@angular/core';
import {LanguagePairDataService} from "../../dataservice/language-pair-data-service";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";
import {LanguagePair} from "../../model/language-pair";

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.scss']
})
export class StudentDashboardComponent implements OnInit {

  languagePairs$: Observable<LanguagePair[]>;

  constructor(private languagePairService: LanguagePairDataService) {

  }

  ngOnInit() {
    this.languagePairs$ = this.languagePairService.entities$;
  }

}
