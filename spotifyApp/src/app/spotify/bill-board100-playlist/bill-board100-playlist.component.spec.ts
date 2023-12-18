import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { of } from 'rxjs';
import { BillBoard100PlaylistComponent } from './bill-board100-playlist.component';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';
import { MusicDataService } from 'src/app/service/data/music-data.service';
import { PlayDialogService } from 'src/app/service/component/play-dialog.service';
import { Track } from 'src/app/model/Track';
import { Wishlist } from 'src/app/model/Wishlist';

describe('BillBoard100PlaylistComponent', () => {
  let component: BillBoard100PlaylistComponent;
  let fixture: ComponentFixture<BillBoard100PlaylistComponent>;

  let wishlistDataServiceSpy: jasmine.SpyObj<WishlistDataService>;
  let musicDataServiceSpy: jasmine.SpyObj<MusicDataService>;
  let playDialogServiceSpy: jasmine.SpyObj<PlayDialogService>;

  beforeEach(() => {
    wishlistDataServiceSpy = jasmine.createSpyObj('WishlistDataService', ['deleteTrackByUsernameAndTrackId', 'saveTrackToWishlist', 'getUserWishList']);
    musicDataServiceSpy = jasmine.createSpyObj('MusicDataService', ['billBoard100Playlist', 'getTrack']);
    playDialogServiceSpy = jasmine.createSpyObj('PlayDialogService', ['openPlayDialog']);

    TestBed.configureTestingModule({
      declarations: [BillBoard100PlaylistComponent],
      providers: [
        { provide: WishlistDataService, useValue: wishlistDataServiceSpy },
        { provide: MusicDataService, useValue: musicDataServiceSpy },
        { provide: PlayDialogService, useValue: playDialogServiceSpy },
      ],
    });

    fixture = TestBed.createComponent(BillBoard100PlaylistComponent);
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
    // const wishlistData: Wishlist = { tracks: [{ id: '123' }, { o: '456' }] };
    // wishlistDataServiceSpy.getUserWishList.and.returnValue(of(wishlistData));

    component.ngOnInit();

    expect(component.trackIds).toEqual(['123', '456']);
  });

  it('should call billBoard100Playlist and set up dataSource and paginator on todayTopHitsPlaylist', () => {
    // const playlistData = { tracks: { items: [{ track: { external_urls: { spotify: 'url' } } }] } };
    // musicDataServiceSpy.billBoard100Playlist.and.returnValue(of(playlistData));

    component.todayTopHitsPlaylist();

    expect(component.dataSource).toBeTruthy();
    expect(component.paginator).toBeTruthy();
  });

});