import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PracticeLessonComponent } from './practice-lesson.component';

describe('PracticeLessonComponent', () => {
  let component: PracticeLessonComponent;
  let fixture: ComponentFixture<PracticeLessonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PracticeLessonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PracticeLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
