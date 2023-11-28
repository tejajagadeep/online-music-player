import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HttpInterceptorBasicAuthService } from './service/http/http-interceptor-basic-auth.service';
import { FavoriteListComponent as FavoriteListComponent } from './favorite-list/favorite-list.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { TodayTopHitsPlaylistComponent } from './spotify/today-top-hits-playlist/today-top-hits-playlist.component';
import { BillBoard100PlaylistComponent } from './spotify/bill-board100-playlist/bill-board100-playlist.component';

@NgModule({
  declarations: [
    AppComponent,
    FavoriteListComponent,
    HomeComponent,
    LoginComponent,
    TodayTopHitsPlaylistComponent,
    BillBoard100PlaylistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
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
