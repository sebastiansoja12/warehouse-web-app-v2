import { TestBed } from '@angular/core/testing';

import { DepotService } from './depot-service.service';

describe('DepotServiceService', () => {
  let service: DepotService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepotService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});