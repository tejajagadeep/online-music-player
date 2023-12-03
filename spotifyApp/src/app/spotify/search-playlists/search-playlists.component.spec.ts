import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPlaylistsComponent } from './search-playlists.component';

describe('SearchPlaylistsComponent', () => {
  let component: SearchPlaylistsComponent;
  let fixture: ComponentFixture<SearchPlaylistsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchPlaylistsComponent]
    });
    fixture = TestBed.createComponent(SearchPlaylistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
