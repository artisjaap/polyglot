import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ManagerTranslationService} from "../../common/services/manager-translation.service";
import {LanguagePairDTO} from "../../common/services/dto/language-pair-dto";

@Component({
  selector: 'pol-translation-simple-add',
  templateUrl: './translation-simple-add.component.html',
  styleUrls: ['./translation-simple-add.component.scss']
})
export class TranslationSimpleAddComponent implements OnInit {
  @ViewChild('file') file;
  public files: Set<File> = new Set();

  @Input() translationPair:LanguagePairDTO;

  constructor(private translationService:ManagerTranslationService) { }

  ngOnInit() {

  }

  addNewTranslation(languageFrom:HTMLInputElement, languageTo:HTMLInputElement){
    this.translationService.addNewWord(this.translationPair.id, languageFrom.value, languageTo.value)
      .subscribe(r =>{});
  }

  onFilesAdded(){
    const files: { [key: string]: File } = this.file.nativeElement.files;
    for (let key in files) {
      if (!isNaN(parseInt(key))) {
        this.files.add(files[key]);
      }
    }

    this.translationService.uploadTranslations(this.translationPair.id, this.files);
  }
}
