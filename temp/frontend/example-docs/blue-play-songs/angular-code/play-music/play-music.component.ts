import { DatePipe } from '@angular/common';
import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
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
  currentAudio: HTMLAudioElement | null = document.querySelector('audio');
  @ViewChild('audioPlayer') audioPlayer!: ElementRef;

  trackDuration = 0;

  isPlaying = false;
  duration = 0;
  currentTime = 0;

  onPlayPauseClick() {
    const audio: HTMLAudioElement = this.audioPlayer.nativeElement;

    if (audio.paused) {
      audio.play();
    } else {
      audio.pause();
    }

    this.isPlaying = !this.isPlaying;
  }

  onTimeUpdate(event: Event) {
    const audio: HTMLAudioElement = this.audioPlayer.nativeElement;
    this.currentTime = audio.currentTime;
    this.duration = audio.duration;
  }

  calculateProgress() {
    if (this.duration === 0) {
      return '0%';
    }

    const progress = (this.currentTime / this.duration) * 100;
    return `${progress}%`;
  }

  getCurrentTime() {
    const minutes = Math.floor(this.currentTime / 60);
    const seconds = Math.floor(this.currentTime % 60);
    return `${this.formatTime(minutes)}:${this.formatTime(seconds)}`;
  }
  seekTo(event: MouseEvent): void {
    const progressBar = event.target as HTMLProgressElement;
    const clickPosition = event.clientX - progressBar.getBoundingClientRect().left;
    const percentClicked = clickPosition / progressBar.clientWidth;
    const seekTime = percentClicked * this.trackDuration;
  
    // Set the audio's current time to the calculated time
    if (this.audioPlayer) {
      this.audioPlayer.nativeElement.currentTime = seekTime;
    }
  }
  
  getTotalTime() {
    this.duration = this.trackDuration
    const minutes = Math.floor(this.duration / 60);
    const seconds = Math.floor(this.duration % 60);
    return `${this.formatTime(minutes)}:${this.formatTime(seconds)}`;
  }

  formatTime(value: number) {
    return value < 10 ? `0${value}` : `${value}`;
  }
  constructor(public dialogRef: MatDialogRef<PlayMusicComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { track: any, playlistId: any },
    // private datePipe: DatePipe
  ) {
    this.track = this.trackList[this.trackIndex];
  }

  ngOnInit(): void {
    this.track = this.data.track
    this.trackIndex = this.track.index - 1
    this.trackList = this.data.playlistId
    console.log(this.trackIndex)

    console.log(this.trackList.length)
    console.log(this.trackList)

  }

  initializeAudioPlayer() {
    // Access the native HTML audio element
    const audio: HTMLAudioElement = this.audioPlayer.nativeElement;

    // Update progress as the audio is played
    audio.addEventListener('timeupdate', () => {
      // Update progress bar based on the current time and duration
      const progress = (audio.currentTime / audio.duration) * 100;
      // Update your progress bar style or value accordingly
      // Example: this.progressWidth = `${progress}%`;
    });

    // Update the current time and total time when metadata is loaded
    audio.addEventListener('loadedmetadata', () => {
      // Access audio duration and update total time
      const totalTime = this.formatTime(audio.duration);
      // Update your total time span accordingly
      // Example: this.totalTime = totalTime;
    });
  }
  updateTime(event: any) {
    this.currentTime = event.target.currentTime;
    this.trackDuration = event.target.duration;
  }

  // formatTime(seconds: number): number {
  //   // const date = new Date();
  //   // date.setSeconds(seconds);
  //   // return this.datePipe.transform(date, 'HH:mm:ss') || '';
  //   return seconds
  // }



  playCurrentTrack() {

    if (this.trackList[this.trackIndex].preview_url === null) {
      this.onNextPlay()
    }
    this.track = this.trackNull;
    setTimeout(() => {
      this.track = this.trackList[this.trackIndex];
    }, 1000);

  }

  onCloseClick(): void {
    this.dialogRef.close();
    this.track.preview_url = ''
  }

  onBackClick(): void {
    if (this.trackIndex === 0) {
      this.trackIndex = this.trackList.length
    }
    this.trackIndex = (this.trackIndex - 1) % this.trackList.length;
    this.playCurrentTrack();
  }
  onNextClick(): void {

    // console.log(this.track.preview_url)
    console.log("hello print")


    if (this.trackIndex === this.trackList.length) {
      this.trackIndex = -1
    }
    this.trackIndex = (this.trackIndex + 1) % this.trackList.length;
    console.log(this.trackIndex)


    this.playCurrentTrack();

  }
  openEternal(link: any) {
    window.open(link, '_blank');
  }

  onNextPlay() {
    setTimeout(() => {
      this.onNextClick();
    }, 5000);
  }
}
