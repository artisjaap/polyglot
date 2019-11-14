import {Component, Input, OnInit} from '@angular/core';
import {LanguagePair} from "../model/language-pair";
import {FormBuilder, FormGroup} from "@angular/forms";
import {LanguagePairDataService} from "../../../language-pair-data-service";

@Component({
  selector: 'app-language-pair-card',
  templateUrl: './language-pair-card.component.html',
  styleUrls: ['./language-pair-card.component.scss']
})
export class LanguagePairCardComponent implements OnInit {
  private form: FormGroup;

  private mode : 'VIEW' | 'NEW';

  @Input()
  languagePair: LanguagePair;

  constructor(private fb:FormBuilder, private languagePairService:LanguagePairDataService) {
    this.form = this.fb.group({
      languageFrom: ['from'],
      languageTo: ['to']
    })


  }

  ngOnInit() {
    if(this.languagePair) {
      this.mode = 'VIEW';
    }else {
      this.mode = 'NEW';
    }
  }

  createPair() {
    const languagePair =  this.form.value;
    this.languagePairService.add(languagePair);
  }

  removePair() {
    this.languagePairService.delete(this.languagePair);
  }

  openPair() {

  }
}
