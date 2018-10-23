import {LessonTranslationPairResponse} from "./lesson-translation-pair-response";

export interface LessonResponse {
  languagePairId:string;
  name:string;
  userId:string;
  translations:LessonTranslationPairResponse[];

}
