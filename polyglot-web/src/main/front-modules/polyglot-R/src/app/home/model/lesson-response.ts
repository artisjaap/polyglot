import {TranslationForLessonResponse} from "./translation-for-lesson-response";

export class LessonResponse {
  id: string;
  name: string;
  languagePairId: string;
  translations: TranslationForLessonResponse[];
}
