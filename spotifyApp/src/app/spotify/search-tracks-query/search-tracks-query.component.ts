import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-tracks-query',
  templateUrl: './search-tracks-query.component.html',
  styleUrls: ['./search-tracks-query.component.css']
})
export class SearchTracksQueryComponent {

  searchQuery!: string

  constructor(private router: Router) {}

  searchTracks(query: string) {
    this.router.navigate(['/search-tracks', query]);
  }
}
