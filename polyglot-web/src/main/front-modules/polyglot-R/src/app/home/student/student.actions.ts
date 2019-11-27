import {createAction, props} from "@ngrx/store";
import {Translation} from "../model/translation";
import {LessonHeader} from "../model/lesson-header";
import {Lesson} from "../model/lesson";
import {Update} from "@ngrx/entity";
import {NewLessonRequest} from "../model/new-lesson-request";
import {NewTranslation} from "../model/new-translation";
import {LanguagePairResponse} from "../model/language-pair-response";
import {LessonHeaderResponse} from "../model/lesson-header-response";
import {LessonResponse} from "../model/lesson-response";
import {TranslationForLessonResponse} from "../model/translation-for-lesson-response";
import {NewTranslationForLessonRequest} from "../model/new-translation-for-lesson-request";


export const loadAllLanguagePairs = createAction(
  "[] load all language pairs"
);

export const allLanguagePairsLoaded = createAction(
  "[] all language pairs loaded",
  props<{languagePairs : LanguagePairResponse[]}>()
);

export const loadLessonHeaders = createAction(
  "[] loadLessonHeaders",
  props<{languagePairId:string}>()
);

export const lessonHeadersLoaded = createAction(
  "[] lessonHeadersLoaded",
  props<{lessonHeaders:LessonHeaderResponse[]}>()
);

export const loadLesson = createAction(
  "[] load lesson",
  props<{lessonId: string}>()
);

export const lessonLoaded = createAction(
  "[] lessonLoaded",
  props<{lesson: LessonResponse}>()
);

export const creaetLesson = createAction(
  "[] create lesson",
  props<{lesson:NewLessonRequest}>()
);

export const lessonCreated = createAction(
  "[] lessonCreated",
  props<{lesson: LessonHeaderResponse}>()
);

export const addNewTranslationToLesson = createAction(
  "[] add translation to lesson",
  props<{ translation: NewTranslationForLessonRequest }>()
);

export const newTranslationAdded = createAction(
  "[] new translation added",
  props<{translation:TranslationForLessonResponse}>()
);

export const deleteTranslationFromLesson = createAction(
  "[] delete translation from lesson",
  props<{ translation: TranslationForLessonResponse; }>()
);

export const translationFromLessonDeleted = createAction(
  "[] translation deleted from lesson",
  props<{translation:LessonResponse}>()
)

export const addExistingTranslationToLesson = createAction(
  "[] add existing translation to lesson",
  props<{lessonId:string, translationId: string}>()
);

export const existingTranslationDeleteFromLesson = createAction(
  "[] existing translation deleted from lesson",
  props<{lessonId:string, translationId: string}>()
);



