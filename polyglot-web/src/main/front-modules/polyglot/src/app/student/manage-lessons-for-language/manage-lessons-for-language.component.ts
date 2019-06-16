import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ManagerTranslationService} from "../../core/services/manager-translation.service";
import {LessonService} from "../../core/services/lesson.service";
import {LanguagePairResponse} from "../../core/services/response/language-pair-response";
import {PageNavigation} from "../../core/paging/page-navigation";
import {Observable} from "rxjs";
import {PagedResponse} from "../../core/services/request/paged-response";
import {LessonHeaderResponse} from "../../core/services/response/lesson-header-response";
import {NgForm} from "@angular/forms";
import {PracticeWordResponse} from "../../core/services/response/practice-word-response";

@Component({
  selector: 'pol-manage-lessons-for-language',
  templateUrl: './manage-lessons-for-language.component.html',
  styleUrls: ['./manage-lessons-for-language.component.scss']
})
export class ManageLessonsForLanguageComponent implements OnInit {
  @ViewChild('f', {static: true}) lessonForm: NgForm;

  languagePair: LanguagePairResponse;
  pageNav: PageNavigationImpl;
  private filteredLessons: PagedResponse<LessonHeaderResponse>;


  constructor(private route: ActivatedRoute,
              private translationService: ManagerTranslationService,
              private lessonService: LessonService) { }

  ngOnInit() {
    const languagePairId = this.route.snapshot.params['languagePairId'];
    this.pageNav = new PageNavigationImpl(0, 10, this.lessonService, languagePairId);

    this.translationService.languagePairWithId(languagePairId).subscribe(response => {
      this.languagePair = response;
    });

    this.pageNav.event.subscribe(r => {
      this.filteredLessons = r;
    });

    this.pageNav.goToFirstPage();
  }

  getControl() : PageNavigation<LessonHeaderResponse> {
    return this.pageNav;
  }

  createLesson() {
    this.lessonService.createLesson(this.languagePair.id, this.lessonForm.value.lessonName)
      .subscribe(r => {
        this.pageNav.goToFirstPage();
      });
  }


}


class PageNavigationImpl extends PageNavigation<LessonHeaderResponse> {

  constructor(page: number, pageSize: number, private lessonService: LessonService
    , private languagePairId: string) {
    super(page, pageSize);
  }

  doSearch(page: number, pageSize: number, text: string): Observable<PagedResponse<LessonHeaderResponse>> {
    return this.lessonService.allLessonsForLanguage(text, this.languagePairId, page, pageSize);

  }

}
