import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguagePairDetailComponent } from './language-pair-detail.component';

describe('LanguagePairDetailComponent', () => {
  let component: LanguagePairDetailComponent;
  let fixture: ComponentFixture<LanguagePairDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguagePairDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguagePairDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
