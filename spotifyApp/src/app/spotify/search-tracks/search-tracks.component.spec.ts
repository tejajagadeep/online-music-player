import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTracksComponent } from './search-tracks.component';

describe('SearchTracksComponent', () => {
  let component: SearchTracksComponent;
  let fixture: ComponentFixture<SearchTracksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchTracksComponent]
    });
    fixture = TestBed.createComponent(SearchTracksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
