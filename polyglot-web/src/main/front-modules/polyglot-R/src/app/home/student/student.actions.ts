import {createAction, props} from '@ngrx/store';
import {LanguagePairResponse} from '../model/language-pair-response';
import {LessonHeaderResponse} from '../model/lesson-header-response';
import {LessonResponse} from '../model/lesson-response';
import {TranslationForLessonResponse} from '../model/translation-for-lesson-response';
import {NewTranslationForLessonRequest} from '../model/new-translation-for-lesson-request';
import {NewLessonHeaderRequest} from '../model/new-lesson-header-request';


export const loadAllLanguagePairs = createAction(
  '[] load all language pairs'
);

export const allLanguagePairsLoaded = createAction(
  '[] all language pairs loaded',
  props<{languagePairs: LanguagePairResponse[]}>()
);

export const createLanguagePair = createAction(
  '[] create language pair',
  props<{languagePair: LanguagePairResponse}>()
);

export const languagePairCreated = createAction(
  '[] language pair created',
  props<{languagePair: LanguagePairResponse}>()
);

export const deleteLanguagePair = createAction(
  '[] delete language pair',
  props<{languagePair: LanguagePairResponse}>()
);


export const languagePairDeleted = createAction(
  '[] language pair deleted',
  props<{languagePair: LanguagePairResponse}>()
);


export const loadLessonHeaders = createAction(
  '[] loadLessonHeaders',
  props<{languagePairId: string}>()
);

export const lessonHeadersLoaded = createAction(
  '[] lessonHeadersLoaded',
  props<{languagePairId: string, lessonHeaders: LessonHeaderResponse[]}>()
);

export const loadLesson = createAction(
  '[] load lesson',
  props<{lessonId: string}>()
);

export const lessonLoaded = createAction(
  '[] lessonLoaded',
  props<{lesson: LessonResponse}>()
);

export const createLesson = createAction(
  '[] create lesson',
  props<{lesson: NewLessonHeaderRequest}>()
);

export const lessonCreated = createAction(
  '[] lesson created',
  props<{languagePairId: string, lessonHeader: LessonHeaderResponse}>()
);

export const deleteLesson = createAction(
  '[] delete lesson',
  props<{lessonId: string}>()
)

export const lessonDeleted = createAction(
  '[] lesson deleted',
  props<{lessonHeader: LessonHeaderResponse}>()
)

export const addNewTranslationToLesson = createAction(
  '[] add translation to lesson',
  props<{ translation: NewTranslationForLessonRequest }>()
);

export const newTranslationAdded = createAction(
  '[] new translation added to lesson',
  props<{translation: TranslationForLessonResponse}>()
);

export const deleteTranslationFromLesson = createAction(
  '[] delete translation from lesson',
  props<{ translationId: string, lessonId: string}>()
);

export const translationFromLessonDeleted = createAction(
  '[] translation deleted from lesson',
  props<{lesson: LessonResponse}>()
);

export const addExistingTranslationToLesson = createAction(
  '[] add existing translation to lesson',
  props<{lessonId: string, translationId: string}>()
);

export const existingTranslationDeleteFromLesson = createAction(
  '[] existing translation deleted from lesson',
  props<{lessonId: string, translationId: string}>()
);



