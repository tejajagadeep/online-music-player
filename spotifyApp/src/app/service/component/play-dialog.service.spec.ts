import { TestBed } from '@angular/core/testing';

import { PlayDialogService } from './play-dialog.service';

describe('PlayDialogService', () => {
  let service: PlayDialogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlayDialogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
