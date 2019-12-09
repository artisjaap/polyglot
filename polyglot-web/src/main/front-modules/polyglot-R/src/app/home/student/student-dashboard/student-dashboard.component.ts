import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {AppState} from '../../../reducers';
import {languagePairs} from '../student.selectors';
import {LanguagePairService} from '../../services/language-pair.service';
import {LanguagePairResponse} from '../../model/language-pair-response';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.scss']
})
export class StudentDashboardComponent implements OnInit {

  languagePairs$: Observable<LanguagePairResponse[]>;

  constructor(private state: Store<AppState>) {

  }
  //
  ngOnInit() {
    this.languagePairs$ = this.state.select(languagePairs);
  //   this.languagePairs$ = this.languagePairService.entities$;
  }

}
