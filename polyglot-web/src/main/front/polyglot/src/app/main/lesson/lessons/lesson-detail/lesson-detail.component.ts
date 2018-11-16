import { Component, OnInit } from '@angular/core';
import {LessonService} from "../../../common/services/lesson.service";
import {ActivatedRoute} from "@angular/router";
import {LessonResponse} from "../../../common/services/response/lesson-response";
import {PageNavigation} from "../../../common/components/page-navigation/page-navigation";
import {PracticeWordResponse} from "../../../common/services/response/practice-word-response";
import {PracticeTranslationService} from "../../../common/services/practice-translation.service";
import {Observable} from "rxjs";
import {PagedResponse} from "../../../common/services/request/paged-response";

@Component({
  selector: 'pol-lesson-detail',
  templateUrl: './lesson-detail.component.html',
  styleUrls: ['./lesson-detail.component.scss']
})
export class LessonDetailComponent implements OnInit {

  lessonId:string;
  lessonData: LessonResponse;

  pageNav: PageNavigationImpl;
  filteredTranslations: PagedResponse<PracticeWordResponse>;

  constructor(private route: ActivatedRoute,
              private lessonService:LessonService,
              private practiceTranslationService:PracticeTranslationService) { }

  ngOnInit() {
    this.lessonId = this.route.snapshot.params['lessonId'];
    let languagePairId = this.route.snapshot.params['pairId'];

    this.pageNav = new PageNavigationImpl(0, 10, this.practiceTranslationService, languagePairId);


    this.lessonService.practiceLesson(this.lessonId)
      .subscribe(r => this.lessonData = r);

    this.pageNav.event.subscribe(r => {
      this.filteredTranslations = r
    });
  }

  getControl() : PageNavigation<PracticeWordResponse> {
    return this.pageNav;
  }

  addWordToLesson(translationId:string){
    this.lessonService.addWordToLesson(this.lessonId, translationId)
      .subscribe(r => this.lessonData.translations.push(r));

  }

  removeWordFromLesson(translationId:string, index:number){
    this.lessonService.removeWordFromLesson(this.lessonId, translationId)
      .subscribe(r => this.lessonData.translations.splice(index, 1));
  }
}

class PageNavigationImpl extends PageNavigation<PracticeWordResponse> {

  constructor(page:number, pageSize:number, private practiceTranslationService:PracticeTranslationService, private languagePairId:string){
    super(page, pageSize);
  }

  doSearch(page: number, pageSize: number, text: string): Observable<PagedResponse<PracticeWordResponse>> {
    return this.practiceTranslationService.allWordsForLanguagePair(text, this.languagePairId, page, pageSize);

  }

}
