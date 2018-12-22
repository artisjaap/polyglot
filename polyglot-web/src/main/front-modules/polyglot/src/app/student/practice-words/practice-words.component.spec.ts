import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PracticeWordsComponent } from './practice-words.component';

describe('PracticeWordsComponent', () => {
  let component: PracticeWordsComponent;
  let fixture: ComponentFixture<PracticeWordsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PracticeWordsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PracticeWordsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
