import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StudentRoutingModule} from './student-routing.module';
import {StudentDashboardComponent} from './student-dashboard/student-dashboard.component';
import {EntityDefinitionService, EntityMetadataMap} from '@ngrx/data';
import {LanguagePairCardComponent} from './language-pair-card/language-pair-card.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {LanguagePairComponent} from './language-pair/language-pair.component';
import {LanguagePairDetailComponent} from './language-pair-detail/language-pair-detail.component';
import {StudentMainComponent} from './student-main/student-main.component';
import {EffectsModule} from '@ngrx/effects';
import {StudentEffects} from './reducers/student.effects';
import { StoreModule } from '@ngrx/store';
import * as fromStudent from './reducers/student.reducer';
import { LessonDetailComponent } from './lesson-detail/lesson-detail.component';
import { PracticeLessonComponent } from './practice-lesson/practice-lesson.component';
import {FileSaverModule} from 'ngx-filesaver';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {FaIconLibrary, FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {faCoffee, faRedo, faUndo} from '@fortawesome/free-solid-svg-icons';
import {TranslationEditorComponent} from './components/translation-editor/translation-editor.component';



const entityMetadata: EntityMetadataMap = {
  LanguagePair: {},
  LessonHeader: {},
  Lesson: {},
  Translation: {}
};


@NgModule({
  declarations: [StudentDashboardComponent, LanguagePairCardComponent, LanguagePairComponent, LanguagePairDetailComponent, StudentMainComponent, LessonDetailComponent, PracticeLessonComponent, TranslationEditorComponent],
  imports: [
    CommonModule,
    StudentRoutingModule,
    MatIconModule,
    MatCardModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    FileSaverModule,
    EffectsModule.forFeature([StudentEffects]),
    StoreModule.forFeature(fromStudent.studentFeatureKey, fromStudent.reducers),
    MatCheckboxModule,
    FontAwesomeModule
  ],
  providers: []
})

export class StudentModule {

  constructor(private eds: EntityDefinitionService, library: FaIconLibrary) {
    eds.registerMetadataMap(entityMetadata);
    library.addIcons(faUndo, faRedo);

  }

}
