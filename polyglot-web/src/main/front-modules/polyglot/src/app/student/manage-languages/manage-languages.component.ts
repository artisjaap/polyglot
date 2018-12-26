import {Component, OnInit, ViewChild} from '@angular/core';
import {ManagerTranslationService} from "../../core/services/manager-translation.service";
import {NgForm} from "@angular/forms";
import {LanguagePairResponse} from "../../core/services/response/language-pair-response";

@Component({
  selector: 'pol-manage-languages',
  templateUrl: './manage-languages.component.html',
  styleUrls: ['./manage-languages.component.scss']
})
export class ManageLanguagesComponent implements OnInit {
  @ViewChild("f") form: NgForm;

  languagePairs: LanguagePairResponse[];

  constructor(private manageTranslationsService:ManagerTranslationService) { }

  ngOnInit() {
    this.manageTranslationsService.allLanguagePairs().subscribe(r => {
      this.languagePairs = r;
    });
  }

  createLanguagePair() {
    this.manageTranslationsService.createNewLanguagePair(this.form.value.languageFrom,this.form.value.languageTo).subscribe(r => {
      this.languagePairs.push(r);
      this.form.reset();
    });
  }


}
