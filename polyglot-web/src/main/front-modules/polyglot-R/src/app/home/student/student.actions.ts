import {createAction, props} from "@ngrx/store";
import {Translation} from "../model/translation";
import {LessonHeader} from "../model/lesson-header";
import {Lesson} from "../model/lesson";
import {Update} from "@ngrx/entity";
import {NewLessonRequest} from "../model/new-lesson-request";
import {NewTranslation} from "../model/new-translation";




/* translations */
export const loadLatestWords = createAction(
  "[LanguagePair Resolver] Load latest words",
  props<{ languagePairId: string }>()
);

export const latestWordsLoaded = createAction(
  "[LanguagePair Resolver] Latest words loaded",
  props<{ translationPairId: string, translations: Translation[] }>()
);

/* lesson headers */
export const loadLatestLessons = createAction(
  "[LanguagePair Resolver] Load latest lessons",
  props<{ languagePairId: string }>()
);

export const latestLessonsLoaded = createAction(
  "[LanguagePair Resolver] Latest lessons loaded",
  props<{ translationPairId: string, lessons: LessonHeader[] }>()
);


/* lessons */
export const loadLessonById = createAction(
  "[LessonDetail Resolver] Load lesson for id",
  props<{ lessonId: string }>()
);

export const lessonLoaded = createAction(
  "[LessonDetail Resolver] Lesson loaded",
  props<{ lesson: Lesson }>()
);

export const removeTranslationFromLesson = createAction(
  "[Lesson Detail] remove translation from lesson",
  props<{ lessonId:string, translationId:string, updatedLesson : Update<Lesson> }>()
)

export const createNewLesson = createAction(
  "[Language Pair Detail] create new lesson",
  props<{ lesson: NewLessonRequest }>()
)

export const newLessonCreated = createAction(
  "[Student Effect] new lesson created",
  props<{ lesson: Lesson }>()
)

export const removeLesson = createAction(
  "[Language Pair Detail] remove lesson",
  props<{ languagePairId: string, lessonHeader: LessonHeader; }>()
);

export const addNewTranslation = createAction(
  "[Lesson Detail] add translation",
  props<{ lessonId: string, translation: NewTranslation, updatedLesson:Lesson }>()
);
