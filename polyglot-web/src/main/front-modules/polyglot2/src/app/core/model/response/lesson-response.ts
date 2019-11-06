import {LessonTranslationPairResponse} from "./lesson-translation-pair-response";

export interface LessonResponse {
  id:string;
  languagePairId:string;
  name:string;
  userId:string;
  translations:LessonTranslationPairResponse[];
}
