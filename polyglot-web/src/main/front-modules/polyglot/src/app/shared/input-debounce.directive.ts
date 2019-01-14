import {Directive} from '@angular/core';
import {Input} from '@angular/core';
import {ElementRef} from '@angular/core';
import {HostListener} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {OnInit} from '@angular/core';
import {debounceTime} from 'rxjs/operators';
import {Output} from '@angular/core';
import {EventEmitter} from '@angular/core';

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
