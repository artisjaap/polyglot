import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageTrainComponent } from './language-train.component';

describe('LanguageTrainComponent', () => {
  let component: LanguageTrainComponent;
  let fixture: ComponentFixture<LanguageTrainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguageTrainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguageTrainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
