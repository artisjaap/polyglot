import {Component, OnInit, ViewChild} from '@angular/core';
import {AppState} from '../../../reducers';
import {Store} from '@ngrx/store';
import {ActivatedRoute, ActivatedRouteSnapshot} from '@angular/router';
import {latestTranslationsForLanguagePair, lessonHeadersForLanguagePair} from '../student.selectors';
import {LessonHeaderResponse} from '../../model/lesson-header-response';
import {Observable} from 'rxjs';
import {StudentActions} from '../action-types';
import {NewLessonHeaderRequest} from '../../model/new-lesson-header-request';
import {FileUpload} from '../../model/file-upload';
import {TranslationForLessonResponse} from '../../model/translation-for-lesson-response';
import {PracticeAnswerService} from "../../services/practice-answer.service";
import {FileSaverService} from "ngx-filesaver";

@Component({
  selector: 'app-language-pair-detail',
  templateUrl: './language-pair-detail.component.html',
  styleUrls: ['./language-pair-detail.component.scss']
})
export class LanguagePairDetailComponent implements OnInit {

  lessonHeaders$: Observable<LessonHeaderResponse[]>;
  latestTranslations$: Observable<TranslationForLessonResponse[]>;
  languagePairId: string;

  @ViewChild('file', {static: true}) file;
  public files: Set<File> = new Set();

  constructor(private store: Store<AppState>
              , private route: ActivatedRoute
              , private practiceAnswerService: PracticeAnswerService
              , private fileSaverService: FileSaverService) {
  }

  ngOnInit() {
    this.languagePairId = this.route.snapshot.params.languagePairId;
    this.lessonHeaders$ = this.store.select(lessonHeadersForLanguagePair, {languagePairId: this.languagePairId});
    this.latestTranslations$ = this.store.select(latestTranslationsForLanguagePair, {languagePairId: this.languagePairId});
  }

  deleteLesson(lessonToDelete: LessonHeaderResponse) {
    this.store.dispatch(StudentActions.deleteLesson({lessonId: lessonToDelete.id}));
  }

  createLesson(newLesson: HTMLInputElement) {
    const lesson: NewLessonHeaderRequest = {
      languagePairId: this.languagePairId,
      name: newLesson.value
    };
    this.store.dispatch(StudentActions.createLesson({lesson}));
  }

  onFilesAdded() {
    const files: { [key: string]: File } = this.file.nativeElement.files;
    let file: File ;
    for (const key in files) {
      if (!isNaN(parseInt(key, 10))) {
        this.files.add(files[key]);
        file = files[key];
      }
    }

    const fileUpload: FileUpload = {languagePairId: this.languagePairId, file};

    this.store.dispatch(StudentActions.uploadTranslationFile({fileUpload}));


  }

  generateLessonPdf() {
    this.practiceAnswerService.createPracticePdf({
       languagePairId: this.languagePairId,
      lessonId: null,
       numberOfWords: 50
    }).subscribe(data => this.fileSaverService.save(data.body, "test.pdf"));
  }

  generateLessonPdForLesson(lessonId: string) {
    this.practiceAnswerService.createPracticePdf({
      languagePairId: this.languagePairId,
      lessonId,
      numberOfWords: 0
    }).subscribe(data => this.fileSaverService.save(data.body, "test.pdf"));
  }


}


