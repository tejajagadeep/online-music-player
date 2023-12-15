import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-playlist-query',
  templateUrl: './search-playlist-query.component.html',
  styleUrls: ['./search-playlist-query.component.css']
})
export class SearchPlaylistQueryComponent {
  searchQuery!: string

  constructor(private router: Router) {}

  searchTracks(query: string) {
    this.router.navigate(['/search-playlists', query]);
  }
}
