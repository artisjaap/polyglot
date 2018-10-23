import {CachableDTO} from "./cachable-dto";
import {LanguagePairResponse} from "../response/language-pair-response";
import {isArray} from "util";

export class LanguagePairsCached extends CachableDTO<LanguagePairResponse[]>{

  public push(data:LanguagePairResponse){
    this.getData().push(data);
  }

  public findById(id:string) : LanguagePairResponse {
    let data = this.getData();
    if (isArray(data)) {
      for (let i = 0; i < data.length; i++) {
        if (data[i].id === id) {
          return data[i];
        }
      }
    }
    return undefined;
  }
}
