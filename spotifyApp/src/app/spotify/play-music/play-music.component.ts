import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Track } from 'src/app/model/Track';

@Component({
  selector: 'app-play-music',
  templateUrl: './play-music.component.html',
  styleUrls: ['./play-music.component.css']
})
export class PlayMusicComponent implements OnInit {

  track!: Track;
  id!: string;

  constructor(public dialogRef: MatDialogRef<PlayMusicComponent>,
    @Inject(MAT_DIALOG_DATA) public data:{track: any},
    ) {}

  ngOnInit(): void {
    this.track = this.data.track
    // this.playSong(this.data.id);
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
