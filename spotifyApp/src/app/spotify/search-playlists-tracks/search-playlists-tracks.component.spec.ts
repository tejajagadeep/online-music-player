import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPlaylistsTracksComponent } from './search-playlists-tracks.component';

describe('SearchPlaylistsTracksComponent', () => {
  let component: SearchPlaylistsTracksComponent;
  let fixture: ComponentFixture<SearchPlaylistsTracksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchPlaylistsTracksComponent]
    });
    fixture = TestBed.createComponent(SearchPlaylistsTracksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
