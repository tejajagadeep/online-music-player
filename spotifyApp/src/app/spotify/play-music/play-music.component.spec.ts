import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayMusicComponent } from './play-music.component';

describe('PlayMusicComponent', () => {
  let component: PlayMusicComponent;
  let fixture: ComponentFixture<PlayMusicComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PlayMusicComponent]
    });
    fixture = TestBed.createComponent(PlayMusicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
