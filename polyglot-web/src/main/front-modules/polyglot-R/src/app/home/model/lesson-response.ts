import {TranslationForLessonResponse} from "./translation-for-lesson-response";

export class LessonResponse {
  id: string;
  title: string;
  translations: TranslationForLessonResponse[];
}
