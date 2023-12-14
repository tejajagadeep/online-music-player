import { TestBed } from '@angular/core/testing';

import { PlaylistGetService } from './playlist-get.service';

describe('PlaylistGetService', () => {
  let service: PlaylistGetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlaylistGetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
