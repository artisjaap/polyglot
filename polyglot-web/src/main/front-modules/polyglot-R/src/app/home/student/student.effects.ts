import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {StudentActions} from "./action-types";
import {concatMap, map} from "rxjs/operators";
import {TranslationService} from "../services/translation-service";
import {LessonService} from "../services/lesson-service";

@Injectable()
export class StudentEffects {

  /*Learn: The effect kicks in when a loadLatestWord action is dispatched
  * - we need to enrich the loaded translations with the languagePair it triggered
  * - then an action is dispatched that contains the translations and the language pair
  * - now, the reducer will use this to save the data in the store
  * */
  loadLatestWordsForTranslationPair$ = createEffect(
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
        this.lessonService.loadLatestLessonsForLanguagePair(action.languagePairId)
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



  constructor(private actions$: Actions, private translationService: TranslationService, private lessonService: LessonService) {

  }

}
