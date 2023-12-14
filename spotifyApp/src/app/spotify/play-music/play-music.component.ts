import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Track } from 'src/app/model/Track';
import { DataService } from 'src/app/service/component/data.service';
import { PlaylistGetService } from 'src/app/service/component/playlist-get.service';
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
  trackNull!: Track;
  id!: string;
  audioPlayer: HTMLAudioElement = new Audio();

  constructor(public dialogRef: MatDialogRef<PlayMusicComponent>,
    @Inject(MAT_DIALOG_DATA) public data:{track: any, playlistId: any},
    ) {
    this.track = this.trackList[this.trackIndex];
    }

  ngOnInit(): void {
    this.track = this.data.track
    this.trackIndex = this.track.index
    this.trackList = this.data.playlistId
  }

  onNextClick(): void {
    this.trackIndex = (this.trackIndex + 1) % this.trackList.length;
    console.log(this.trackIndex)
    this.playCurrentTrack();
  }
  setupAudioPlayer(): void {
    this.audioPlayer.remove()

    this.audioPlayer.currentTime = 0;
    if (this.track.preview_url!==null) {
    this.audioPlayer.src = this.track.preview_url;
    }
    this.audioPlayer.load();
    this.audioPlayer.play();
    this.audioPlayer.addEventListener('ended', () => this.onNextClick());
  }

  playCurrentTrack(){
    this.track = this.trackNull;
    setTimeout(() => {
    this.track = this.trackList[this.trackIndex];
    }, 1000);
    
    console.log(this.track.preview_url);
  }

  onCloseClick(): void {
    this.dialogRef.close();
    this.track.preview_url=''
  }
  
  onBackClick(): void {
    if(this.trackIndex===0){
      this.trackIndex=this.trackList.length
    }
    this.trackIndex = (this.trackIndex - 1) % this.trackList.length;
    this.playCurrentTrack();
  }
}
