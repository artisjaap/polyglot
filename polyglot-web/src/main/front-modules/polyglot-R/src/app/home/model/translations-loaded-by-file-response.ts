import {TranslationForLessonResponse} from './translation-for-lesson-response';
import {LessonHeaderResponse} from './lesson-header-response';

export class TranslationsLoadedByFileResponse {
  lessonHeaderResponse: LessonHeaderResponse;
  languagePairId: string;
  translation: TranslationForLessonResponse;

}
