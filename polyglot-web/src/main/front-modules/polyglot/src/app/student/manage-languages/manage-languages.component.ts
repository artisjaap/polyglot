import {Component, OnInit, ViewChild} from '@angular/core';
import {ManagerTranslationService} from '../../core/services/manager-translation.service';
import {NgForm} from '@angular/forms';
import {LanguagePairResponse} from '../../core/services/response/language-pair-response';
import {LanguagePairRequest} from "../../core/services/request/LanguagePairRequest";
import {UtilService} from "../../core/services/util.service";

@Component({
  selector: 'pol-manage-languages',
  templateUrl: './manage-languages.component.html',
  styleUrls: ['./manage-languages.component.scss']
})
export class ManageLanguagesComponent implements OnInit {
  @ViewChild('f',  {static: true}) form: NgForm;

  languagePairs: LanguagePairResponse[];

  constructor(private manageTranslationsService: ManagerTranslationService,
              private utilService: UtilService) {

  }

  ngOnInit() {
    this.manageTranslationsService.allLanguagePairs().subscribe(r => {
      this.languagePairs = r;
    });
  }

  createLanguagePair() {
    this.manageTranslationsService.createNewLanguagePair(this.form.value.languageFrom, this.form.value.languageTo).subscribe(r => {
      this.languagePairs.push(r);
      this.form.reset();
    });
  }

  removeLanguagePair(l:LanguagePairResponse){
    this.manageTranslationsService.removeLanguagePair(l.id).subscribe(r => {
      this.utilService.removeById(this.languagePairs, r.id);
    });
  }


}
