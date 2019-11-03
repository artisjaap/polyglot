import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LessonListItemComponent } from './lesson-list-item.component';

describe('LessonListItemComponent', () => {
  let component: LessonListItemComponent;
  let fixture: ComponentFixture<LessonListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LessonListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LessonListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
