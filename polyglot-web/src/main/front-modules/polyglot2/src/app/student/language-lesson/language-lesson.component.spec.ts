import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageLessonComponent } from './language-lesson.component';

describe('LanguageLessonComponent', () => {
  let component: LanguageLessonComponent;
  let fixture: ComponentFixture<LanguageLessonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguageLessonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguageLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
