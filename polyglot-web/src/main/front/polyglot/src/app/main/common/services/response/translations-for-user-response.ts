import {TranslationPairResponse} from "./translation-pair-response";

export interface TranslationsForUserResponse {
  userId:string;
  languagePairId:string;
  translations:TranslationPairResponse[];
}
