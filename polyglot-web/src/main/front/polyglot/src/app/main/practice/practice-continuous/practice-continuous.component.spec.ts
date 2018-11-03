import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PracticeContinuousComponent } from './practice-continuous.component';

describe('PracticeContinuousComponent', () => {
  let component: PracticeContinuousComponent;
  let fixture: ComponentFixture<PracticeContinuousComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PracticeContinuousComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PracticeContinuousComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
