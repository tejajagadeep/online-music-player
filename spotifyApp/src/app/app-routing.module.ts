import { NgModule, inject } from '@angular/core';
import { RouterModule, RouterStateSnapshot, Routes } from '@angular/router';
import { FavoriteListComponent } from './favorite-list/favorite-list.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { BillBoard100PlaylistComponent } from './spotify/bill-board100-playlist/bill-board100-playlist.component';
import { TodayTopHitsPlaylistComponent } from './spotify/today-top-hits-playlist/today-top-hits-playlist.component';
import { DiscoverWeeklyPlaylistComponent } from './spotify/discover-weekly-playlist/discover-weekly-playlist.component';
import { SearchTracksComponent } from './spotify/search-tracks/search-tracks.component';
import { SearchPlaylistsComponent } from './spotify/search-playlists/search-playlists.component';
import { SearchPlaylistsTracksComponent } from './spotify/search-playlists-tracks/search-playlists-tracks.component';
import { AuthGuard, LoginGuard } from './service/http/auth-guard.service';
import { LogoutComponent } from './logout/logout.component';
import { ProfileComponent } from './users/profile/profile.component';
import { UpdateProfileComponent } from './users/update-profile/update-profile.component';
import { SearchTracksQueryComponent } from './spotify/search-tracks-query/search-tracks-query.component';
import { PlayMusicComponent } from './spotify/play-music/play-music.component';



const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'search-tracks', component: SearchTracksQueryComponent, canActivate: [AuthGuard]},
  { path: 'search-tracks/:query', component: SearchTracksComponent, canActivate: [AuthGuard]},
  { path: 'search-playlists', component: SearchPlaylistsComponent, canActivate: [AuthGuard]},
  { path: 'playlist-track/:playlistId', component: SearchPlaylistsTracksComponent, canActivate: [AuthGuard]},
  { path: 'billboard-hot-100-playlist', component: BillBoard100PlaylistComponent, canActivate: [AuthGuard]},
  { path: 'workout-playlist', component: DiscoverWeeklyPlaylistComponent, canActivate: [AuthGuard]}, //
  { path: 'top-hits-playlist', component: TodayTopHitsPlaylistComponent, canActivate: [AuthGuard]},
  { path: 'favorites', component: FavoriteListComponent , canActivate: [AuthGuard]},
  { path: 'profile', component: ProfileComponent , canActivate: [AuthGuard]},
  { path: 'update-profile', component: UpdateProfileComponent , canActivate: [AuthGuard]},
  { path: 'logout', component: LogoutComponent},
  { path: 'login', component: LoginComponent , canActivate: [LoginGuard]},
  { path: '**', redirectTo: 'home'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }