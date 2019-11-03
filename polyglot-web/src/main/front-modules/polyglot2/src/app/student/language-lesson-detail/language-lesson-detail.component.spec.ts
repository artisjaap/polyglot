import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageLessonDetailComponent } from './language-lesson-detail.component';

describe('LanguageLessonDetailComponent', () => {
  let component: LanguageLessonDetailComponent;
  let fixture: ComponentFixture<LanguageLessonDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguageLessonDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguageLessonDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
