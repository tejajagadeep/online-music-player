import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPlaylistQueryComponent } from './search-playlist-query.component';

describe('SearchPlaylistQueryComponent', () => {
  let component: SearchPlaylistQueryComponent;
  let fixture: ComponentFixture<SearchPlaylistQueryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchPlaylistQueryComponent]
    });
    fixture = TestBed.createComponent(SearchPlaylistQueryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
