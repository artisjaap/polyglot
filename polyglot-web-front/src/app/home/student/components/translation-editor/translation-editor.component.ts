import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SimpleTranslation} from "../../../../common/model/simple-translation";
import {Form, FormBuilder, FormGroup} from "@angular/forms";
import {debounceTime, tap} from 'rxjs/operators';
import {Action, Store} from '@ngrx/store';
import {AppState} from "../../../../reducers/app.reducer";
import {StudentActions} from "../../action-types";
import {UpdateTranslationForLessonRequest} from '../../../model/update-translation-for-lesson-request';
import {NewTranslationForLessonRequest} from '../../../model/new-translation-for-lesson-request';
import {Actions, ofType} from '@ngrx/effects';
import { v4 as uuidv4 } from 'uuid';
import {UpdateTranslationForLessonAction} from '../../../model/update-translation-for-lesson-action';
import {NewTranslationForLessonAction} from '../../../model/new-translation-for-lesson-action';

@Component({
  selector: 'app-translation-editor',
  templateUrl: './translation-editor.component.html',
  styleUrls: ['./translation-editor.component.scss']
})
export class TranslationEditorComponent implements OnInit {

  @Input()
  public languageA: string;
  @Input()
  public languageB: string;
  @Input()
  public lessonId: string;
  @Input()
  public translationId: string;
  @Input()
  public languagePairId: string;
  @Output()
  public changed: EventEmitter<SimpleTranslation> = new EventEmitter<SimpleTranslation>();

  public form: FormGroup;
  private uuid: string;

  constructor(private fb: FormBuilder, private store: Store<AppState>, private actions$: Actions) {

    this.uuid = uuidv4();
    actions$.pipe(ofType(
      StudentActions.translationUpdated,
      StudentActions.newTranslationAdded
    ), tap(translation => {

      if (translation.uuid === this.uuid) {
        console.log('mark as prestine');
        this.form.markAsPristine();
      }
    })).subscribe();


  }

  ngOnInit(): void {
    this.form = this.fb.group({
      languageA: [this.languageA],
      languageB: [this.languageB]
    });

    this.form.valueChanges.pipe(debounceTime(2000)).subscribe(val => {
      if(this.translationId){
        this.store.dispatch(StudentActions.updateTranslation({ translation : new UpdateTranslationForLessonRequest( this.lessonId, this.translationId, [this.form.value.languageA], [this.form.value.languageB]), uuid: this.uuid}));
      }else {
        this.store.dispatch(StudentActions.addNewTranslationToLesson({translation: new NewTranslationForLessonRequest( this.languagePairId, this.lessonId, [this.form.value.languageA], [this.form.value.languageB]), uuid: this.uuid}));
      }
    });
  }

  removeWordFromLesson() {
    this.store.dispatch(StudentActions.deleteTranslationFromLesson({translationId : this.translationId, lessonId: this.lessonId}));
  }

}
