import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { of } from 'rxjs';
import { DiscoverWeeklyPlaylistComponent } from './discover-weekly-playlist.component';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';
import { MusicDataService } from 'src/app/service/data/music-data.service';
import { PlayDialogService } from 'src/app/service/component/play-dialog.service';
import { Track } from 'src/app/model/Track';

describe('DiscoverWeeklyPlaylistComponent', () => {
  let component: DiscoverWeeklyPlaylistComponent;
  let fixture: ComponentFixture<DiscoverWeeklyPlaylistComponent>;

  let wishlistDataServiceSpy: jasmine.SpyObj<WishlistDataService>;
  let musicDataServiceSpy: jasmine.SpyObj<MusicDataService>;
  let playDialogServiceSpy: jasmine.SpyObj<PlayDialogService>;

  beforeEach(() => {
    wishlistDataServiceSpy = jasmine.createSpyObj('WishlistDataService', ['deleteTrackByUsernameAndTrackId', 'saveTrackToWishlist', 'getUserWishList']);
    musicDataServiceSpy = jasmine.createSpyObj('MusicDataService', ['getDiscoverWeeklyPlaylist']);
    playDialogServiceSpy = jasmine.createSpyObj('PlayDialogService', ['openPlayDialog']);

    TestBed.configureTestingModule({
      declarations: [DiscoverWeeklyPlaylistComponent],
      providers: [
        { provide: WishlistDataService, useValue: wishlistDataServiceSpy },
        { provide: MusicDataService, useValue: musicDataServiceSpy },
        { provide: PlayDialogService, useValue: playDialogServiceSpy },
      ],
    });

    fixture = TestBed.createComponent(DiscoverWeeklyPlaylistComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call todayTopHitsPlaylist and wishListTracks on ngAfterViewInit', () => {
    spyOn(component, 'todayTopHitsPlaylist');
    spyOn(component, 'wishListTracks');

    component.ngAfterViewInit();

    expect(component.todayTopHitsPlaylist).toHaveBeenCalled();
    expect(component.wishListTracks).toHaveBeenCalled();
  });

  it('should call deleteTrackToWishList and saveTrackToWishList1 when toggleHeartState is called', () => {
    const trackId = { id: '123' } as Track;
    component.trackIds = ['123'];

    spyOn(component, 'deleteTrackToWishList');
    spyOn(component, 'saveTrackToWishList1');

    component.toggleHeartState(trackId);

    expect(component.deleteTrackToWishList).toHaveBeenCalled();
    expect(component.saveTrackToWishList1).not.toHaveBeenCalled();
  });

  it('should call saveTrackToWishList1 when toggleHeartState is called and trackId not in trackIds', () => {
    const trackId = { id: '456' } as Track;
    component.trackIds = ['123'];

    spyOn(component, 'deleteTrackToWishList');
    spyOn(component, 'saveTrackToWishList1');

    component.toggleHeartState(trackId);

    expect(component.saveTrackToWishList1).toHaveBeenCalled();
    expect(component.deleteTrackToWishList).not.toHaveBeenCalled();
  });

  it('should call getHeartState and return correct state', () => {
    component.trackIds = ['123'];
    component.heartStates = { '123': 'active' };

    const result = component.getHeartState('123');

    expect(result).toBe('active');
  });

  it('should call wishListTracks and populate trackIds on initialization', () => {
    // const wishlistData = { tracks: [{ id: '123' }, { id: '456' }] };
    // wishlistDataServiceSpy.getUserWishList.and.returnValue(of(wishlistData));

    component.ngOnInit();

    expect(component.trackIds).toEqual(['123', '456']);
  });

  it('should call getDiscoverWeeklyPlaylist and set up dataSource and paginator on todayTopHitsPlaylist', () => {
    // const playlistData = { tracks: { items: [{ track: { external_urls: { spotify: 'url' } } }] } };
    // musicDataServiceSpy.getDiscoverWeeklyPlaylist.and.returnValue(of(playlistData));

    component.todayTopHitsPlaylist();

    expect(component.dataSource).toBeTruthy();
    expect(component.paginator).toBeTruthy();
  });

 
});