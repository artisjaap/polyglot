import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WordPairListComponent } from './word-pair-list.component';

describe('WordPairListComponent', () => {
  let component: WordPairListComponent;
  let fixture: ComponentFixture<WordPairListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WordPairListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WordPairListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
