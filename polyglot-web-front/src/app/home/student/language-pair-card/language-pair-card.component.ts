import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LanguagePairResponse} from '../../model/language-pair-response';
import {AppState} from '../../../reducers/app.reducer';
import {Store} from '@ngrx/store';
import {StudentActions} from '../action-types';

@Component({
  selector: 'app-language-pair-card',
  templateUrl: './language-pair-card.component.html',
  styleUrls: ['./language-pair-card.component.scss']
})
export class LanguagePairCardComponent implements OnInit {
  form: FormGroup;

  mode: 'VIEW' | 'NEW';

  @Input()
  languagePair: LanguagePairResponse;

  constructor(private fb: FormBuilder, private state: Store<AppState>) {
    this.form = this.fb.group({
      languageA: ['from'],
      languageB: ['to']
    });


  }

  ngOnInit() {
    if (this.languagePair) {
      this.mode = 'VIEW';
    } else {
      this.mode = 'NEW';
    }
  }

  createPair() {
    const languagePair =  this.form.value;
    this.state.dispatch(StudentActions.createLanguagePair({languagePair}));
  }

  removePair() {
    this.state.dispatch(StudentActions.deleteLanguagePair({languagePair: this.languagePair}));
  }

  openPair() {

  }
}
