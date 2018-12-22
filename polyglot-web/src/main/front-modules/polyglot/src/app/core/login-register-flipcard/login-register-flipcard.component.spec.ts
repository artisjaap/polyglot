import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginRegisterFlipcardComponent } from './login-register-flipcard.component';

describe('LoginRegisterFlipcardComponent', () => {
  let component: LoginRegisterFlipcardComponent;
  let fixture: ComponentFixture<LoginRegisterFlipcardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginRegisterFlipcardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginRegisterFlipcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
