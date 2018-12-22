import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageWordsForLanguageComponent } from './manage-words-for-language.component';

describe('ManageWordsForLanguageComponent', () => {
  let component: ManageWordsForLanguageComponent;
  let fixture: ComponentFixture<ManageWordsForLanguageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageWordsForLanguageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageWordsForLanguageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
