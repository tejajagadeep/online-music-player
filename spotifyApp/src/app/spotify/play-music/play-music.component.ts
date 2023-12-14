import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Track } from 'src/app/model/Track';
import { DataService } from 'src/app/service/component/data.service';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-play-music',
  templateUrl: './play-music.component.html',
  styleUrls: ['./play-music.component.css']
})
export class PlayMusicComponent implements OnInit {
  trackIndex = 0; // Initialize with the first track
  trackList: any[] = []; 
  track!: Track;
  id!: string;

  constructor(public dialogRef: MatDialogRef<PlayMusicComponent>,
    @Inject(MAT_DIALOG_DATA) public data:{track: any},
    private dataService: DataService,
    private musicService: MusicDataService
    ) {
    this.track = this.trackList[this.trackIndex];
    }

  ngOnInit(): void {
    this.track = this.data.track
    // this.playSong(this.data.id);
    this.getPlaylistSearch("37i9dQZF1DXcBWIGoYBM5M")
  }

  getPlaylistSearch(playlistId: string) {
    this.musicService.getPlaylist(playlistId).subscribe({
      next: (v) => {
        v.tracks.items.forEach(element => {
          this.trackList.push(element.track)
        });
      },
      error: (e) => console.error(e),
      complete: () => {console.info('complete')}
    });
  }

  onNextClick(): void {
    this.trackIndex = (this.trackIndex + 1) % this.trackList.length;
    this.playCurrentTrack();
  }

  playCurrentTrack(): void {
    this.track = this.trackList[this.trackIndex];
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }
}
