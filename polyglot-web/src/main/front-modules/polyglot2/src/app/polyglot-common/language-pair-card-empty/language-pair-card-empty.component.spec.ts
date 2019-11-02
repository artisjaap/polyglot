import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguagePairCardEmptyComponent } from './language-pair-card-empty.component';

describe('LanguagePairCardEmptyComponent', () => {
  let component: LanguagePairCardEmptyComponent;
  let fixture: ComponentFixture<LanguagePairCardEmptyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguagePairCardEmptyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguagePairCardEmptyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
