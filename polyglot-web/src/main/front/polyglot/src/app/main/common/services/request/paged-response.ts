export interface PagedResponse<T> {
  page:number;
  pageSize:number;
  numberOfPages:number;
  lastPage:boolean;
  data:T[];

}
