import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PracticeLessonSpeechComponent } from './practice-lesson-speech.component';

describe('PracticeLessonSpeechComponent', () => {
  let component: PracticeLessonSpeechComponent;
  let fixture: ComponentFixture<PracticeLessonSpeechComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PracticeLessonSpeechComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PracticeLessonSpeechComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
