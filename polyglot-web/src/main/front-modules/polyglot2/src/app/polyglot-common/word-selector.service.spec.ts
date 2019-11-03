import { TestBed } from '@angular/core/testing';

import { WordSelectorService } from './word-selector.service';

describe('WordSelectorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WordSelectorService = TestBed.get(WordSelectorService);
    expect(service).toBeTruthy();
  });
});
