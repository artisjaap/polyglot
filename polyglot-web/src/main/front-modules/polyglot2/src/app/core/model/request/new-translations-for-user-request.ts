import {NewSimpleTranslationRequest} from "./new-simple-translation-request";

export interface NewTranslationsForUserRequest {
  userId:string;
  languagePairId:string;
  translations:NewSimpleTranslationRequest[];
}
