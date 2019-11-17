import {LessonTranslationPair} from "./lesson-translation-pair";

export interface Lesson {
  id:string;
  languagePairId:string;
  name:string;
  userId:string;
  translations:LessonTranslationPair[];
}
