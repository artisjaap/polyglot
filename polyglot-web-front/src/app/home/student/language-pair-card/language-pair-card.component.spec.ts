import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguagePairCardComponent } from './language-pair-card.component';

describe('LanguagePairCardComponent', () => {
  let component: LanguagePairCardComponent;
  let fixture: ComponentFixture<LanguagePairCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguagePairCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguagePairCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
