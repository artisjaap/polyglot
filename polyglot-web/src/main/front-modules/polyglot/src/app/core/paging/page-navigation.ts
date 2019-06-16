
import {EventEmitter} from '@angular/core';
import {Observable} from 'rxjs/index';
import {PagedResponse} from '../services/request/paged-response';
import {map} from 'rxjs/operators';

export abstract class PageNavigation<T> {
  private lastPage = 0;
  public numberOfPages;

  public event: EventEmitter<PagedResponse<T>> = new EventEmitter<PagedResponse<T>>();

  constructor(public currentPage: number, public pageSize: number) {

  }

  abstract doSearch(page: number, pageSize: number, text: string): Observable<PagedResponse<T>>;

  private doSearchAndUpdate(page: number, pageSize: number, text: string): Observable<PagedResponse<T>> {
    return this.doSearch(page, pageSize, text)
      .pipe(map(r => {
        this.lastPage = r.numberOfPages - 1;
        this.numberOfPages = r.numberOfPages ;
        this.currentPage = r.page;
        this.pageSize = r.pageSize;
        return r;
      }));
  }

  goToFirstPage() {
    this.doSearchAndUpdate(0, this.pageSize, '')
      .subscribe(r => this.event.next(r))
    ;
  }

  goToLastPage() {
    const subscription = this.doSearchAndUpdate(this.lastPage, this.pageSize, '')
      .subscribe(r => {this.event.next(r); subscription.unsubscribe(); });
  }

  goToNextPage() {
    const subscription = this.doSearchAndUpdate(Math.min(this.lastPage, this.currentPage + 1), this.pageSize, '')
      .subscribe(r => {
        this.event.next(r);
        subscription.unsubscribe();
      })
    ;
  }

  goToPreviousPage() {
    const subscription = this.doSearchAndUpdate(Math.max(0, this.currentPage - 1), this.pageSize, '')
      .subscribe(r => {
        this.event.next(r);
        subscription.unsubscribe();
      })
    ;
  }

  searchForText(text: string) {
    const subscription = this.doSearchAndUpdate(0, this.pageSize, text)
      .subscribe(r => {
        this.event.next(r);
        subscription.unsubscribe();
      });
  }

}
