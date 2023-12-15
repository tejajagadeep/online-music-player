import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Track } from 'src/app/model/Track';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';
import { heartAnimation } from 'src/app/app-parsers/animation-trigger';
import { CommunicationService } from 'src/app/service/component/communication.service';

@Component({
  selector: 'app-play-music',
  templateUrl: './play-music.component.html',
  styleUrls: ['./play-music.component.css'],
  animations: [heartAnimation]
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

  isShuffle= true

  trackIds: String[] = [];
  indexI!: number[];
  heartStates: { [key: string]: string } = {};

  constructor(public dialogRef: MatDialogRef<PlayMusicComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { track: any, playlistId: any },
    private wishList: WishlistDataService,
    private communicationService: CommunicationService
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
    this.wishListTracks()

  }


  toggleHeartState(trackId: Track): void {
    if (!this.trackIds.includes(trackId.id)) {
      if (this.heartStates[trackId.id] === 'active') {
        this.heartStates[trackId.id as any] = 'inactive';
        this.deleteTrackToWishList(trackId.id);
      } else {
        this.heartStates[trackId.id as any] = 'active'
        this.saveTrackToWishList1(trackId);
      }
    } else {
      if (this.heartStates[trackId.id] === 'inactive') {
        this.heartStates[trackId.id as any] = 'active';
        this.saveTrackToWishList1(trackId);
      } else {
        this.heartStates[trackId.id as any] = 'inactive'
        this.deleteTrackToWishList(trackId.id);
      }
    }
  }

  wishListTracks() {
    this.wishList.getUserWishList().subscribe({
      next: (a) => {
        a.tracks.forEach(track => this.trackIds.push(track.id))
      },
      error: (e) => console.error(e),
      complete: () => console.log('tracks added to wishlist')
    })
  }

  getHeartState(trackId: string): string {
    if (this.trackIds.includes(trackId)) {
      return this.heartStates[trackId] || 'active';
    } else {
      return this.heartStates[trackId] || 'inactive';
    }
  }
  
  deleteTrackToWishList(id: string) {

    this.wishList.deleteTrackByUsernameAndTrackId(id).subscribe({
      next: (a) => {
        console.log('track deleted')
      },
      error: (e) => console.error(e),
      complete: () => { console.info('complete'); this.communicationService.callMethod(); }
    });
  }

  saveTrackToWishList1(id: Track) {
    this.wishList.saveTrackToWishlist(id).subscribe({
      next: (a) => {
        console.log(a)
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });;
  }
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
  updateTime(event: any) {
    this.currentTime = event.target.currentTime;
    this.trackDuration = event.target.duration;
  }

  playCurrentTrack() {

    if (this.trackList[this.trackIndex].preview_url === null) {
      this.onNextPlay()
    }
    this.isPlaying=false

    // this.track = this.trackNull;
    setTimeout(() => {
      this.track = this.trackList[this.trackIndex];
    }, 1000);

  }

  shuffleTracks(){
    this.isShuffle=false;
    this.trackList = shuffleArray(this.trackList);
  }

  sortTracksByIndex() {
    this.isShuffle=true;
    this.trackList.sort((a, b) => a.index - b.index);
    this.trackIndex=this.track.index-1
  }
  

  onCloseClick(): void {
    this.dialogRef.close();
    this.track.preview_url = ''
  }

  onBackClick(): void {
    this.isPlaying=true;

    if (this.trackIndex === 0) {
      this.trackIndex = 1
    }
    this.trackIndex = (this.trackIndex - 1) % this.trackList.length;
    this.playCurrentTrack();
  }
  onNextClick(): void {
    this.isPlaying=true;
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
    }, 3000);
    this.trackList
  }
  playTrack(link: any) {
    window.open(link, '_blank');
  }
}

function shuffleArray(array: any[]): any[] {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
}