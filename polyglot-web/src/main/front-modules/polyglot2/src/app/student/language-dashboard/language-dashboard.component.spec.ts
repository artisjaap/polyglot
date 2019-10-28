import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageDashboardComponent } from './language-dashboard.component';

describe('LanguageDashboardComponent', () => {
  let component: LanguageDashboardComponent;
  let fixture: ComponentFixture<LanguageDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguageDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguageDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
