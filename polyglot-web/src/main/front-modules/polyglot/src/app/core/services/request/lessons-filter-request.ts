import {PageableFilter} from "./pageable-filter";

export class LessonsFilterRequest extends PageableFilter {
  constructor(public textFilter: string, public languagePairId: string, public pageSize: number,  public pageNumber: number) {
    super(pageSize, pageNumber);

  }
}
