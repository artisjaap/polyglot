import {NewSimpleTranslationRequest} from "./new-simple-translation-request";

export class NewTranslationsForUserRequest {
  constructor(public userId:string, public languagePairId:string, public translations:NewSimpleTranslationRequest[]){}
}
