import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


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
    SearchTracksComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule
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
