import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SimpleTranslation} from "../../../../common/model/simple-translation";
import {Form, FormBuilder, FormGroup} from "@angular/forms";
import {debounceTime} from "rxjs/operators";
import {Store} from "@ngrx/store";
import {AppState} from "../../../../reducers/app.reducer";
import {StudentActions} from "../../action-types";

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

  constructor(private fb: FormBuilder, private store: Store<AppState>) {



  }

  ngOnInit(): void {
    this.form = this.fb.group({
      languageA: [this.languageA],
      languageB: [this.languageB]
    });

    this.form.valueChanges.pipe(debounceTime(2000)).subscribe(val => {
      this.store.dispatch(StudentActions.updateTranslation(this.form.value))
    });
  }

}
