import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HttpInterceptorBasicAuthService } from './service/http/http-interceptor-basic-auth.service';
import { FavoriteListComponent as FavoriteListComponent } from './favorite-list/favorite-list.component';
import { HomeComponent } from './navigation/home/home.component';
import { LoginComponent } from './login/login.component';
import { TodayTopHitsPlaylistComponent } from './spotify/today-top-hits-playlist/today-top-hits-playlist.component';
import { BillBoard100PlaylistComponent } from './spotify/bill-board100-playlist/bill-board100-playlist.component';
import { MillisecondsToMinutesPipe } from './app-parsers/pipe-calculate';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DateFormatPipe } from './app-parsers/pipe-date';
import { RemoveTimePipe } from './app-parsers/pipe-remove';
import { DiscoverWeeklyPlaylistComponent } from './spotify/discover-weekly-playlist/discover-weekly-playlist.component';
import { FooterComponent } from './navigation/footer/footer.component';
import { SearchTracksComponent } from './spotify/search-tracks/search-tracks.component';
import { FormsModule } from '@angular/forms';
import { SearchPlaylistsComponent } from './spotify/search-playlists/search-playlists.component';
import { SearchPlaylistsTracksComponent } from './spotify/search-playlists-tracks/search-playlists-tracks.component';
import { TopBarComponent } from './navigation/top-bar/top-bar.component';
import { SideBarComponent } from './navigation/side-bar/side-bar.component';
import { LogoutComponent } from './logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    FavoriteListComponent,
    HomeComponent,
    LoginComponent,
    TodayTopHitsPlaylistComponent,
    BillBoard100PlaylistComponent,
    MillisecondsToMinutesPipe,
    DateFormatPipe,
    RemoveTimePipe,
    DiscoverWeeklyPlaylistComponent,
    FooterComponent,
    SearchTracksComponent,
    SearchPlaylistsComponent,
    SearchPlaylistsTracksComponent,
    TopBarComponent,
    SideBarComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatPaginatorModule,
    FormsModule,
  ],
  providers: [{

    provide: HTTP_INTERCEPTORS,
  
    useClass: HttpInterceptorBasicAuthService,
  
    multi: true
  
  }],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
