import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTracksQueryComponent } from './search-tracks-query.component';

describe('SearchTracksQueryComponent', () => {
  let component: SearchTracksQueryComponent;
  let fixture: ComponentFixture<SearchTracksQueryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchTracksQueryComponent]
    });
    fixture = TestBed.createComponent(SearchTracksQueryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
