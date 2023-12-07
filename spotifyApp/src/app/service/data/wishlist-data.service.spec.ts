import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { WishlistDataService } from './wishlist-data.service';
import { API_URL_WISHLIST } from 'src/app/app-constants';
import { Track } from 'src/app/model/Track';
import { Wishlist } from 'src/app/model/Wishlist';
import { Album } from 'src/app/model/Album';
import { Artist } from 'src/app/model/Artist';
import { HttpParams } from '@angular/common/http';

describe('WishlistDataService', () => {
  let service: WishlistDataService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [WishlistDataService]
    });

    service = TestBed.inject(WishlistDataService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get user wishlist', () => {

    const mockAlbum = new Album();
    const mockArtist = new Artist();
    const mockTrack = new Track();
    mockTrack.album = mockAlbum;
    mockTrack.artists = [mockArtist];
    mockTrack.id = '123' 

    const mockWishlist = new Wishlist();
    mockWishlist.username = 'user';
    mockWishlist.tracks = [
      mockTrack
    ];
    
    
    service.getUserWishList().subscribe((wishlist) => {
      expect(wishlist).toEqual(mockWishlist);
    });
    const params = new HttpParams().set('username', "username");
    const req = httpTestingController.expectOne(`${API_URL_WISHLIST}/userWishList`);
    expect(req.request.method).toEqual('GET');

    req.flush(mockWishlist);

    httpTestingController.verify();
  });

  it('should save track to wishlist', () => {
    const mockAlbum = new Album();
    const mockArtist = new Artist();
    const mockTrack = new Track();
    mockTrack.album = mockAlbum;
    mockTrack.artists = [mockArtist];
    mockTrack.id = '123' 
    
    service.saveTrackToWishlist(mockTrack).subscribe((savedTrack) => {
      expect(savedTrack).toEqual(mockTrack);
    });

    const req = httpTestingController.expectOne(`${API_URL_WISHLIST}/saveTrackToWishlist`);
    expect(req.request.method).toEqual('POST');

    req.flush(mockTrack);

    httpTestingController.verify();
  });

  it('should delete track by username and trackId', () => {
    const mockTrackId = 'mockTrackId';
    
    service.deleteTrackByUsernameAndTrackId(mockTrackId).subscribe((response) => {
      // handle the response as needed
    });

    const req = httpTestingController.expectOne(`${API_URL_WISHLIST}/removeTrack`);
    expect(req.request.method).toEqual('DELETE');

    req.flush({}); // send a mock response

    httpTestingController.verify();
  });

  afterEach(() => {
    httpTestingController.verify();
  });
});
