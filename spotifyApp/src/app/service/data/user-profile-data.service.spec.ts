import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserProfileDataService } from './user-profile-data.service';
import { UserProfile } from 'src/app/model/UserProfile';
import { API_URL_USERPROFILE } from 'src/app/app-constants';

describe('UserProfileDataService', () => {
  let service: UserProfileDataService;
  let httpMock: HttpTestingController;

  const mockUserProfile: UserProfile = new UserProfile();


  beforeEach(() => {

    mockUserProfile.username = 'username';

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserProfileDataService]
    });

    service = TestBed.inject(UserProfileDataService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should register a new user', () => {
    const username = 'testuser';
    const password = 'testpassword';
    const email = 'testuser@example.com';

    service.register(username, password, email).subscribe(userProfile => {
      expect(userProfile).toBeDefined();
      // Add more expectations based on the response or behavior you expect.
    });

    const request = httpMock.expectOne(`${API_URL_USERPROFILE}/addUser`);
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual(jasmine.objectContaining({ username, password, email }));
    request.flush(mockUserProfile);
  });

  it('should get user by ID', () => {
    const mockUsername = 'testuser';
    localStorage.setItem('authenticatedUser', mockUsername);

    service.getUserById().subscribe(userProfile => {
      expect(userProfile).toBeDefined();
      // Add more expectations based on the response or behavior you expect.
    });

    const request = httpMock.expectOne(`${API_URL_USERPROFILE}/getUserById/${mockUsername}`);
    expect(request.request.method).toBe('GET');
    request.flush(mockUserProfile);
  });

  it('should update user', () => {
    const mockUser = { /* Provide a mock user object with updated data */ };

    service.updateuser(mockUser).subscribe(userProfile => {
      expect(userProfile).toBeDefined();
      // Add more expectations based on the response or behavior you expect.
    });

    const mockUsername = 'testuser';
    const request = httpMock.expectOne(`${API_URL_USERPROFILE}/update/${mockUsername}`);
    expect(request.request.method).toBe('PUT');
    expect(request.request.body).toEqual(jasmine.objectContaining(mockUser));
    request.flush(mockUserProfile);
  });
});
