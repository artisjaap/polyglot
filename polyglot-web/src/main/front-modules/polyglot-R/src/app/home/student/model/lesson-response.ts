import {LessonTranslationPairResponse} from "../../../../../../polyglot2/src/app/core/model/response/lesson-translation-pair-response";

export interface LessonResponse {
  id:string;
  languagePairId:string;
  name:string;
  userId:string;
  translations:LessonTranslationPairResponse[];
}
