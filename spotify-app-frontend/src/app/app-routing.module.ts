import { NgModule, inject } from '@angular/core';
import { RouterModule, RouterStateSnapshot, Routes } from '@angular/router';
import { canActivateTeam } from './service/http/auth-guard.service';
import { FavoriteListComponent } from './favorite-list/favorite-list.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { BillBoard100PlaylistComponent } from './spotify/bill-board100-playlist/bill-board100-playlist.component';
import { TodayTopHitsPlaylistComponent } from './spotify/today-top-hits-playlist/today-top-hits-playlist.component';



const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'billboard-hot-100-playlist', component: BillBoard100PlaylistComponent},
  { path: 'top-hits-playlist', component: TodayTopHitsPlaylistComponent},
  { path: 'favorites', component: FavoriteListComponent, canActivate: [canActivateTeam] },
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }