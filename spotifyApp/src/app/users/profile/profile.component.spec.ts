import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProfileComponent } from './profile.component';
import { AuthenticationService } from 'src/app/service/data/authentication.service';
import { UserProfileDataService } from 'src/app/service/data/user-profile-data.service';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';
import { of } from 'rxjs';
import { UserProfile } from 'src/app/model/UserProfile';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let authServiceSpy: jasmine.SpyObj<AuthenticationService>;
  let userProfileServiceSpy: jasmine.SpyObj<UserProfileDataService>;
  let wishlistServiceSpy: jasmine.SpyObj<WishlistDataService>;

  beforeEach(() => {
    const authServiceSpyObj = jasmine.createSpyObj('AuthenticationService', ['validateToken']);
    const userProfileServiceSpyObj = jasmine.createSpyObj('UserProfileDataService', ['getUserById']);
    const wishlistServiceSpyObj = jasmine.createSpyObj('WishlistDataService', ['getUserWishList']);

    TestBed.configureTestingModule({
      declarations: [ProfileComponent],
      providers: [
        { provide: AuthenticationService, useValue: authServiceSpyObj },
        { provide: UserProfileDataService, useValue: userProfileServiceSpyObj },
        { provide: WishlistDataService, useValue: wishlistServiceSpyObj }
      ]
    });

    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    authServiceSpy = TestBed.inject(AuthenticationService) as jasmine.SpyObj<AuthenticationService>;
    userProfileServiceSpy = TestBed.inject(UserProfileDataService) as jasmine.SpyObj<UserProfileDataService>;
    wishlistServiceSpy = TestBed.inject(WishlistDataService) as jasmine.SpyObj<WishlistDataService>;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call validateToken on ngOnInit', () => {
    authServiceSpy.validateToken.and.returnValue(of({}));

    component.ngOnInit();

    expect(authServiceSpy.validateToken).toHaveBeenCalled();
  });

  it('should call getUserProfile on ngOnInit', () => {
    const fakeUserProfile: UserProfile = {
      username: 'fakeUsername',
      password: 'fakePassword',
      email: 'fakeEmail',
      firstName: '',
      lastName: '',
      dateOfBirth: ''
    };
    userProfileServiceSpy.getUserById.and.returnValue(of(fakeUserProfile));

    component.ngOnInit();

    expect(userProfileServiceSpy.getUserById).toHaveBeenCalled();
  });

  it('should call getWishlist on ngOnInit', () => {
    
    wishlistServiceSpy.getUserWishList.and.returnValue(of({ username: 'username', tracks: [] }));

    component.ngOnInit();

    expect(wishlistServiceSpy.getUserWishList).toHaveBeenCalled();
  });
});
