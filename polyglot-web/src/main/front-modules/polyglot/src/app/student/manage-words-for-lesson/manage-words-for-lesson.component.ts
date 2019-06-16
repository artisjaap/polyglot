import { Component, OnInit } from '@angular/core';
import {LessonService} from "../../core/services/lesson.service";
import {ActivatedRoute} from "@angular/router";
import {LessonResponse} from "../../core/services/response/lesson-response";
import {PageNavigation} from "../../core/paging/page-navigation";
import {PracticeWordResponse} from "../../core/services/response/practice-word-response";
import {PracticeTranslationService} from "../../core/services/practice-translation.service";
import {Observable} from "rxjs";
import {PagedResponse} from "../../core/services/request/paged-response";

@Component({
  selector: 'pol-mange-words-for-lesson',
  templateUrl: './manage-words-for-lesson.component.html',
  styleUrls: ['./manage-words-for-lesson.component.scss']
})
export class ManageWordsForLessonComponent implements OnInit {
   lesson: LessonResponse;
  pageNav: PageNavigationImpl;
  filteredTranslations: PagedResponse<PracticeWordResponse>;

  constructor(private route: ActivatedRoute,
              private lessonService:LessonService,
              private practiceTranslationService: PracticeTranslationService) { }

  ngOnInit() {
    const lessonId = this.route.snapshot.params['lessonId'];
    this.lessonService.practiceLesson(lessonId).subscribe(r => {
      this.lesson = r;

      this.pageNav = new PageNavigationImpl(0, 10, this.practiceTranslationService, r.languagePairId);

      this.pageNav.event.subscribe(r => {
        this.filteredTranslations = r;
      });

      this.pageNav.goToFirstPage();
    })


  }

  getControl() : PageNavigation<PracticeWordResponse> {
    return this.pageNav;
  }

  removeFromLesson(translationId: string) {
    this.lessonService.removeWordFromLesson(this.lesson.id, translationId).subscribe(r => {
      this.lessonService.practiceLesson(this.lesson.id).subscribe(r => {this.lesson = r;})
    });
  }

  addToLesson(translationId: string) {
    this.lessonService.addWordToLesson(this.lesson.id, translationId).subscribe(r => {
      this.lessonService.practiceLesson(this.lesson.id).subscribe(r => {this.lesson = r;})
    });
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
