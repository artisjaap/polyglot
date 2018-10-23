import {CachableDTO} from "./cachable-dto";
import {TranslationPracticeDTO} from "./translation-practice-dto";

export class TranslationPracticesCached extends CachableDTO<TranslationPracticeDTO[]>{

  public push(data:TranslationPracticeDTO){
    this.getData().push(data);
  }
}
