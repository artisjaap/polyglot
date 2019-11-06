import {IPaging} from "./ipaging";
import {PagedResponse} from "../../../../../polyglot/src/app/core/services/request/paged-response";
import {Observable, Subscription} from "rxjs";
import {map} from "rxjs/operators";
import {EventEmitter, OnDestroy} from "@angular/core";

export abstract class PageNavigation<R,F> implements IPaging, OnDestroy{
  private numberOfPages: number = 0;
  public event: EventEmitter<PagedResponse<R>> = new EventEmitter<PagedResponse<R>>();

  private subscription: Subscription = new Subscription();


  constructor(private currentPage:number, private pageSize:number, private filters:F){

  }

  abstract doSearch(page: number, pageSize: number, filters: F): Observable<PagedResponse<R>>;

  private doSearchAndUpdate(): Observable<PagedResponse<R>> {
    return this.doSearch(this.currentPage, this.pageSize, this.filters)
      .pipe(map(r => {
        this.numberOfPages = r.numberOfPages ;
        this.currentPage = r.page;
        this.pageSize = r.pageSize;
        return r;
      }));
  }

  firstPage() {
    console.log("first");
    this.currentPage = 0;
    this.handleSearch();
  }

  private handleSearch() {
    const subscription = this.doSearchAndUpdate().subscribe(
      r => {
        this.event.next(r);
        // subscription.unsubscribe();
      },
      err => {
      },
      () => {
        // subscription.unsubscribe();
      })
    this.subscription.unsubscribe();
    this.subscription.add(subscription);
  }

  lastPage() {
    console.log("last");
    this.currentPage = this.numberOfPages;
    this.handleSearch();
  }

  nextPage() {
    console.log("next");
    if(this.currentPage < this.numberOfPages){
      this.currentPage++;
      this.handleSearch();
    }
  }

  previousPage() {
    console.log("previous");
    if(this.currentPage > 0){
      this.currentPage--;
      this.handleSearch();
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }


}
