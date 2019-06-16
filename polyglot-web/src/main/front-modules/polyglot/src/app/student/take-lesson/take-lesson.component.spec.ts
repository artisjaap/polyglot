import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TakeLessonComponent } from './take-lesson.component';

describe('TakeLessonComponent', () => {
  let component: TakeLessonComponent;
  let fixture: ComponentFixture<TakeLessonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TakeLessonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TakeLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
