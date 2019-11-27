import {Injectable} from "@angular/core";
import {act, Actions, createEffect, ofType} from "@ngrx/effects";
import {StudentActions} from "./action-types";
import {concatMap, map} from "rxjs/operators";
import {TranslationService} from "../services/translation-service";
import {LessonHeaderService} from "../services/lesson-header.service";
import {LessonService} from "../services/lesson-service";
import {LanguagePairServiceService} from "../services/language-pair-service.service";

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
  )


  constructor(private actions$: Actions,
              private translationService: TranslationService,
              private lessonHeaderService: LessonHeaderService,
              private languagePairService: LanguagePairServiceService,
              private lessonService: LessonService) {

  }

}
