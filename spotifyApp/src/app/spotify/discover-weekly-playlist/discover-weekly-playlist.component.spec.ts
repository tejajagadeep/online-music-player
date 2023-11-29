import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscoverWeeklyPlaylistComponent } from './discover-weekly-playlist.component';

describe('DiscoverWeeklyPlaylistComponent', () => {
  let component: DiscoverWeeklyPlaylistComponent;
  let fixture: ComponentFixture<DiscoverWeeklyPlaylistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DiscoverWeeklyPlaylistComponent]
    });
    fixture = TestBed.createComponent(DiscoverWeeklyPlaylistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
