import {Directive, EventEmitter, HostListener, Input, Output} from '@angular/core';
import {debounceTime} from 'rxjs/operators';
import {Subject} from "rxjs";

@Directive({
  selector: '[polInputDebounce]'
})
export class InputDebounceDirective {

  @Input('time') time: number;
  @Output('appDebounce') appDebounce = new EventEmitter();
  @Output('restart') restart = new EventEmitter();
  private change = new Subject();

  constructor() { }

  @HostListener('keydown', ['$event'])
  keyDown(event) {
    this.restart.next(event);
  }


  @HostListener('keyup', ['$event'])
  keyUp(event) {
    event.preventDefault();
    event.stopPropagation();
    console.log('keyup');
    this.change.next(event);
  }

  calcTime() {
    if (this.time){
      return this.time;
    }
    return 1000;
  }


  ngOnInit(): void {

    this.change.pipe(debounceTime(this.calcTime())).subscribe(e => this.appDebounce.emit(e));
  }
}
