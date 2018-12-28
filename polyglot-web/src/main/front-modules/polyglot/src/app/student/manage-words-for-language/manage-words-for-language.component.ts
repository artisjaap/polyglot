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
  @ViewChild('f') form: NgForm;
  languagePair: LanguagePairResponse;
  pageNav: PageNavigationImpl;
  filteredTranslations: PagedResponse<PracticeWordResponse>;
  @ViewChild('file') file;
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
