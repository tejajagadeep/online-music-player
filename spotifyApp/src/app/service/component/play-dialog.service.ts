// play-dialog.service.ts
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PlayMusicComponent } from 'src/app/spotify/play-music/play-music.component';

@Injectable({
  providedIn: 'root'
})
export class PlayDialogService {
  constructor(private dialog: MatDialog) {}

  openPlayDialog(trackId: string): void {
    this.dialog.open(PlayMusicComponent, {
      data: { trackId }
    });
  }
}
