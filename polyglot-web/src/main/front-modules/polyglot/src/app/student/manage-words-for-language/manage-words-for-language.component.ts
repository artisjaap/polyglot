import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ManagerTranslationService} from '../../core/services/manager-translation.service';
import {NgForm} from '@angular/forms';
import {PracticeTranslationService} from '../../core/services/practice-translation.service';

import {PracticeWordResponse} from '../../core/services/response/practice-word-response';
import {Observable} from 'rxjs';
import {PagedResponse} from '../../core/services/request/paged-response';
import {PageNavigation} from '../../core/paging/page-navigation';
import {LanguagePairResponse} from '../../core/services/response/language-pair-response';

@Component({
  selector: 'pol-manage-words-for-language',
  templateUrl: './manage-words-for-language.component.html',
  styleUrls: ['./manage-words-for-language.component.scss']
})
export class ManageWordsForLanguageComponent implements OnInit {
  @ViewChild('f', {static: true}) form: NgForm;
  selectedWord: PracticeWordResponse;
  languagePair: LanguagePairResponse;
  pageNav: PageNavigationImpl;
  filteredTranslations: PagedResponse<PracticeWordResponse>;
  formInEditMode = true;
  @ViewChild('file', {static: true}) file;
  public files: Set<File> = new Set();

  constructor(private route: ActivatedRoute,
              private translationService: ManagerTranslationService,
              private practiceTranslationService: PracticeTranslationService) { }

  ngOnInit() {
    const languagePairId = this.route.snapshot.params['languagePairId'];
    this.pageNav = new PageNavigationImpl(0, 10, this.practiceTranslationService, languagePairId);

    this.translationService.languagePairWithId(languagePairId).subscribe(response => {
      this.languagePair = response;
    });

    this.pageNav.event.subscribe(r => {
      this.filteredTranslations = r;
    });

    this.pageNav.goToFirstPage();
  }

  getControl() : PageNavigation<PracticeWordResponse> {
    return this.pageNav;
  }

  addNewWord() {
    this.translationService.addNewWord(this.languagePair.id, this.form.value.from, this.form.value.to).subscribe(r => {
      console.log(r);
    });
  }

  editWord() {
    this.translationService.updateTranslation(this.selectedWord.translationId, this.selectedWord.question, this.selectedWord.answer).subscribe( r => {
      this.selectedWord.answer = r.answer;
      this.selectedWord.question = r.question;
    });
  }

  onFilesAdded() {
    const files: { [key: string]: File } = this.file.nativeElement.files;
    for (const key in files) {
      if (!isNaN(parseInt(key, 10))) {
        this.files.add(files[key]);
      }
    }

    this.translationService.uploadTranslations(this.languagePair.id, this.files);
    // [files[0].name].progress.subscribe(null,null,() => {
    //   this.pageNav.goToFirstPage();
    // });
  }

  classForStatus(status: string){
    switch(status){
      case 'KNOWN': return 'fa-battery-full';
      case 'NEW': return 'fa-battery-empty';
      case 'IN_PROGRESS': return 'fa-battery-half';
      case 'ON_HOLD': return 'fa-pause';
      default: return 'fa-times';
    }
  }

  nextStatus(practiceWord: PracticeWordResponse) {
    this.practiceTranslationService.updateStatusOfTranslationToNextCircular(practiceWord.translationId).subscribe(r => {
      practiceWord.wordStatsTO.progressStatus = r.wordStatsTO.progressStatus
    });
  }

  editTranslation(practiceWord: PracticeWordResponse){
    this.formInEditMode = true;
    this.form.value.from = practiceWord.question;
    this.form.value.to = practiceWord.answer;
  }


}


class PageNavigationImpl extends PageNavigation<PracticeWordResponse> {

  constructor(page: number, pageSize: number, private practiceTranslationService: PracticeTranslationService
              , private languagePairId: string) {
    super(page, pageSize);
  }

  doSearch(page: number, pageSize: number, text: string): Observable<PagedResponse<PracticeWordResponse>> {
    return this.practiceTranslationService.allWordsForLanguagePair(text, this.languagePairId, page, pageSize);

  }

}
