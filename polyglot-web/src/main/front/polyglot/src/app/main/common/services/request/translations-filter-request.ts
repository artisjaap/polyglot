import {PageableFilter} from "./pageable-filter";

export class TranslationsFilterRequest extends PageableFilter {
  constructor(public textFilter:string, public languagePairId:string, public pageSize:number,  public pageNumber:number){
    super(pageSize, pageNumber);

  }
}
