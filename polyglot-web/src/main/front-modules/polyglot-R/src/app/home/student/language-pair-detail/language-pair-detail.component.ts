import {Component, OnInit} from '@angular/core';
import {AppState} from '../../../reducers';
import {Store} from '@ngrx/store';
import {ActivatedRoute, ActivatedRouteSnapshot} from '@angular/router';
import {lessonHeadersForLanguagePair} from '../student.selectors';
import {LessonHeaderResponse} from '../../model/lesson-header-response';
import {Observable} from 'rxjs';
import {StudentActions} from '../action-types';
import {NewLessonHeaderRequest} from '../../model/new-lesson-header-request';

@Component({
  selector: 'app-language-pair-detail',
  templateUrl: './language-pair-detail.component.html',
  styleUrls: ['./language-pair-detail.component.scss']
})
export class LanguagePairDetailComponent implements OnInit {

  lessonHeaders$: Observable<LessonHeaderResponse[]>;
  languagePairId: string;

  constructor(private store: Store<AppState>, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.languagePairId = this.route.snapshot.params.languagePairId;
    this.lessonHeaders$ = this.store.select(lessonHeadersForLanguagePair,{languagePairId : this.languagePairId});
  }

  deleteLesson(lessonToDelete: LessonHeaderResponse) {
    this.store.dispatch(StudentActions.deleteLesson({lessonId: lessonToDelete.id}));
  }

  createLesson(newLesson: HTMLInputElement) {
    const lesson: NewLessonHeaderRequest = {
      languagePairId: this.languagePairId,
      name: newLesson.value
    }
    this.store.dispatch(StudentActions.createLesson({lesson}));
  }
}


