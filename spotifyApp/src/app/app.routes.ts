import { Routes } from '@angular/router';
import { BillBoard100PlaylistComponent } from './spotify/bill-board100-playlist/bill-board100-playlist.component';
import { DiscoverWeeklyPlaylistComponent } from './spotify/discover-weekly-playlist/discover-weekly-playlist.component';
import { TodayTopHitsPlaylistComponent } from './spotify/today-top-hits-playlist/today-top-hits-playlist.component';

export const routes: Routes = [
//   { path: 'billboard-hot-100-playlist', component: BillBoard100PlaylistComponent},
  { path: '', component: DiscoverWeeklyPlaylistComponent}, //
//   { path: 'top-hits-playlist', component: TodayTopHitsPlaylistComponent},
];
