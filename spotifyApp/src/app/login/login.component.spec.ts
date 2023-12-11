import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { AuthenticationService } from '../service/data/authentication.service';
import { UserProfileDataService } from '../service/data/user-profile-data.service';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { UserProfile } from '../model/UserProfile';
import { AuthAccessToken } from '../model/AuthAccessToken';


describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: jasmine.SpyObj<AuthenticationService>;
  let userService: jasmine.SpyObj<UserProfileDataService>;
  let router: jasmine.SpyObj<Router>;

  const mockUserProfile: UserProfile = new UserProfile();

  beforeEach(() => {

    mockUserProfile.username = 'username';

    authService = jasmine.createSpyObj('AuthenticationService', ['authenticate']);
    userService = jasmine.createSpyObj('UserProfileDataService', ['register']);
    router = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [
        { provide: AuthenticationService, useValue: authService },
        { provide: UserProfileDataService, useValue: userService },
        { provide: Router, useValue: router },
      ],
    });

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call register method on signUp', () => {
    userService.register.and.returnValue(of());

    component.signUp();

    expect(userService.register).toHaveBeenCalledWith(component.username, component.password, component.email);
    expect(component.successMessage).toEqual('Your Are Register');
    expect(component.errorMessage2).toEqual('');
  });

  it('should call register method on signUp', () => {
    const fakeUserProfile: UserProfile = {
      username: 'fakeUsername',
      password: 'fakePassword',
      email: 'fakeEmail',
      firstName: '',
      lastName: '',
      dateOfBirth: ''
    };
    userService.register.and.returnValue(of(fakeUserProfile));
  
    component.signUp();
  
    expect(userService.register).toHaveBeenCalledWith(component.username, component.password, component.email);
    expect(component.successMessage).toEqual('Successfully registered.');
    expect(component.errorMessage2).toEqual('');
  });
  

  it('should call authenticate method on login', () => {
    const fakeAuthAccessToken: AuthAccessToken = {
      message: 'fakeMessage',
      role: 'fakeRole',
      username: 'fakeUsername',
      jwt_token: 'fakeToken'
    };
    authService.authenticate.and.returnValue(of(fakeAuthAccessToken));
  
    component.login();
  
    expect(authService.authenticate).toHaveBeenCalledWith(component.loginUsername, component.loginPassword);
    expect(localStorage.setItem).toHaveBeenCalledWith('token', fakeAuthAccessToken.jwt_token);
    expect(router.navigate).toHaveBeenCalledWith(['/home']);
  });
  
  

  it('should handle error on login', () => {
    authService.authenticate.and.callFake(() => {
      throw {}; 
    });

    component.login();

    expect(authService.authenticate).toHaveBeenCalledWith(component.loginUsername, component.loginPassword);
    expect(component.errorMessage).toEqual('Authentication failed. Please check your credentials.');
  });
});

