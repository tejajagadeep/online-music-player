// play-dialog.service.ts
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Track } from 'src/app/model/Track';
import { PlayMusicComponent } from 'src/app/spotify/play-music/play-music.component';

@Injectable({
  providedIn: 'root'
})
export class PlayDialogService {
  constructor(private dialog: MatDialog) {}

  openPlayDialog(tracks: Track, trackList : Track[]): void {
    this.dialog.open(PlayMusicComponent, {
      data: { track:tracks, playlistId:trackList },
      width: '80%',
    });
  }

  // openDetailsDialog(data: any): void {
  //   this.dialog.open(PlayMusicComponent, {
  //     width: '400px',
  //     data,
  //   });
  // }

  
}
