import {Injectable} from '@angular/core';
import {act, Actions, createEffect, ofType} from '@ngrx/effects';
import {StudentActions} from './action-types';
import {concatMap, map} from 'rxjs/operators';
import {TranslationService} from '../services/translation-service';
import {LessonHeaderService} from '../services/lesson-header.service';
import {LessonService} from '../services/lesson-service';
import {LanguagePairService} from '../services/language-pair.service';

@Injectable()
export class StudentEffects {

  /*Learn: The effect kicks in when a loadLatestWord action is dispatched
  * - we need to enrich the loaded translations with the languagePair it triggered
  * - then an action is dispatched that contains the translations and the language pair
  * - now, the reducer will use this to save the data in the store
  * */
/*  loadLatestWordsForTranslationPair$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.loadLatestWords),
      concatMap(action =>
           this.translationService.loadLatestWordsForLanguagePair(action.languagePairId)
             .pipe(
               map(translations => {
                 return {translations: translations, languagePairId:action.languagePairId}
                }
             ))
      ),
      map(translations => {
          return StudentActions.latestWordsLoaded({
            translationPairId: translations['languagePairId'],
            translations: translations['translations']
          })
        }
      )
    )
  );


  loadLatestLessonsForTranslationPair$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.loadLatestLessons),
      concatMap(action =>
        this.lessonHeaderService.loadLatestLessonsForLanguagePair(action.languagePairId)
          .pipe(
            map(lessons => {
                return {lessons: lessons, languagePairId:action.languagePairId}
              }
            ))
      ),
      map(lessons => {
          return StudentActions.latestLessonsLoaded({
            translationPairId: lessons['languagePairId'],
            lessons: lessons['lessons']
          })
        }
      )
    )
  );

  loadLessonForId$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.loadLessonById),
      concatMap(action =>
        this.lessonService.loadLessonById(action.lessonId)
      ),
      map(lesson => StudentActions.lessonLoaded({lesson}))
    )
  );

  createNewLesson$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.createNewLesson),
      concatMap(action => this.lessonService.createLesson(action.lesson)),
      map(lesson => StudentActions.newLessonCreated({lesson}))
    )
  );

  removeTranslationFromLesson$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.removeTranslationFromLesson),
      concatMap(action => this.lessonService.removeTranslationFromLesson(action.lessonId, action.translationId))
    ),
  )

  translationAddedForLanguage = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.addNewTranslation),
      concatMap(action => this.lessonService.addNewTranslationToLesson(action.lessonId, action.translation))
    )
  )
*/

  allLanguagePairs$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.loadAllLanguagePairs),
      concatMap(action => this.languagePairService.allLanguagePairs()),
      map(languagePairs => StudentActions.allLanguagePairsLoaded({languagePairs})
      )
    )
  );

  deleteLanguagePair$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.deleteLanguagePair),
      concatMap(action => this.languagePairService.deleteLanguaegPair(action.languagePair)),
      map( languagePair => StudentActions.languagePairDeleted({languagePair}))
    )
  );

  createLanguagePair$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.createLanguagePair),
      concatMap(action => this.languagePairService.creaetLanguaegPair(action.languagePair)),
      map(languagePair => StudentActions.languagePairCreated({languagePair}))
    )
  );

  loadLessonHeaders$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.loadLessonHeaders),
      concatMap(action => this.lessonHeaderService.loadLessonsForLanguagePair(action.languagePairId).pipe(
        map(lessonHeaders => {
          return {languagePairId: action.languagePairId, languageHeaders: lessonHeaders};
        })
      )),
      map(lessonHeaders => StudentActions.lessonHeadersLoaded({languagePairId: lessonHeaders.languagePairId,
        lessonHeaders: lessonHeaders.languageHeaders}))
    )
  );

  createLesson$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.createLesson),
      concatMap( action => this.lessonHeaderService.createNewLesson(action.lesson).pipe(
        map( lessonHeader => {
          return {languagePairId: action.lesson.languagePairId, languageHeader : lessonHeader};
        })
      )),
      map(payload => StudentActions.lessonCreated({languagePairId: payload.languagePairId, lessonHeader: payload.languageHeader}))
    )
  );

  deleteLesson$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.deleteLesson),
      concatMap( action => this.lessonService.deleteLesson(action.lessonId)),
      map(lessonHeader => StudentActions.lessonDeleted({lessonHeader}))
    )
  );

  loadLesson$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.loadLesson),
      concatMap(action => this.lessonService.loadLessonById(action.lessonId)),
      map(lesson => StudentActions.lessonLoaded({lesson}))
    )
  );

  newTranslationForLesson$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.addNewTranslationToLesson),
      concatMap(payload => this.translationService.createNewTranslation(payload.translation)),
      map(translation => StudentActions.newTranslationAdded({translation}))
    )
  );

  removeTranslationFromLesson$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.deleteTranslationFromLesson),
      concatMap(action => this.lessonService.deleteWordFromLesson(action.lessonId, action.translationId)),
      map(lesson => StudentActions.translationFromLessonDeleted({lesson}))
    )
  );

  uploadTranslationFie$ = createEffect(
    () => this.actions$.pipe(
      ofType(StudentActions.uploadTranslationFile),
      concatMap(action => this.translationService.uploadTranslations(action.languagePairId, action.files)),
      map(loadedTranslationsFronFile => StudentActions.translationFileUploaded({loadedTranslationsFronFile}))
    )
  );

  constructor(private actions$: Actions,
              private translationService: TranslationService,
              private lessonHeaderService: LessonHeaderService,
              private languagePairService: LanguagePairService,
              private lessonService: LessonService) {

  }

}
