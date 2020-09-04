import {TranslationForLessonResponse} from "./translation-for-lesson-response";

export class LessonResponse {
  id: string;
  title: string;
  languagePairId: string;
  translations: TranslationForLessonResponse[];


}
