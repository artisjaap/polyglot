import { TestBed } from '@angular/core/testing';

import { AuthenticatedBaseServiceService } from './authenticated-base-service.service';

describe('AuthenticatedBaseServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthenticatedBaseServiceService = TestBed.get(AuthenticatedBaseServiceService);
    expect(service).toBeTruthy();
  });
});
