import { NgModule, inject } from '@angular/core';
import { RouterModule, RouterStateSnapshot, Routes } from '@angular/router';
import { FavoriteListComponent } from './favorite-list/favorite-list.component';
import { HomeComponent } from './navigation/home/home.component';
import { LoginComponent } from './login/login.component';
import { BillBoard100PlaylistComponent } from './spotify/bill-board100-playlist/bill-board100-playlist.component';
import { TodayTopHitsPlaylistComponent } from './spotify/today-top-hits-playlist/today-top-hits-playlist.component';
import { DiscoverWeeklyPlaylistComponent } from './spotify/discover-weekly-playlist/discover-weekly-playlist.component';
import { FooterComponent } from './navigation/footer/footer.component';
import { SearchTracksComponent } from './spotify/search-tracks/search-tracks.component';
import { SearchPlaylistsComponent } from './spotify/search-playlists/search-playlists.component';
import { SearchPlaylistsTracksComponent } from './spotify/search-playlists-tracks/search-playlists-tracks.component';
import { TopBarComponent } from './navigation/top-bar/top-bar.component';
import { SideBarComponent } from './navigation/side-bar/side-bar.component';
import { AuthGuard } from './service/http/auth-guard.service';



const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'test-ui', component: FooterComponent},
  { path: 'search-tracks', component: SearchTracksComponent},
  { path: 'search-playlists', component: SearchPlaylistsComponent},
  { path: 'playlist-track/:playlistId', component: SearchPlaylistsTracksComponent},
  { path: 'billboard-hot-100-playlist', component: BillBoard100PlaylistComponent},
  { path: 'workout-playlist', component: DiscoverWeeklyPlaylistComponent}, //
  { path: 'top-hits-playlist', component: TodayTopHitsPlaylistComponent, canActivate: [AuthGuard]},
  { path: 'favorites', component: FavoriteListComponent },
  { path: 'bar', component: TopBarComponent },
  { path: 'side-bar', component: SideBarComponent },
  // { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }