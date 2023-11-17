import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpotifyMusicComponent } from './spotify-music.component';

describe('SpotifyMusicComponent', () => {
  let component: SpotifyMusicComponent;
  let fixture: ComponentFixture<SpotifyMusicComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SpotifyMusicComponent]
    });
    fixture = TestBed.createComponent(SpotifyMusicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
