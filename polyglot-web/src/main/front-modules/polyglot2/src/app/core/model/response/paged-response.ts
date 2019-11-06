export interface PagedResponse {
  page:number;
  pageSize:number;
  numberOfPages:number;
  lastPage:boolean;
  data:any[];
}
