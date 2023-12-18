import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { AuthenticationService } from '../service/data/authentication.service';
import { UserProfileDataService } from '../service/data/user-profile-data.service';
import { of, throwError } from 'rxjs';
import { UserProfile } from '../model/UserProfile';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authServiceSpy: jasmine.SpyObj<AuthenticationService>;
  let userServiceSpy: jasmine.SpyObj<UserProfileDataService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(() => {
    authServiceSpy = jasmine.createSpyObj('AuthenticationService', ['authenticate']);
    userServiceSpy = jasmine.createSpyObj('UserProfileDataService', ['register']);
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [
        { provide: AuthenticationService, useValue: authServiceSpy },
        { provide: UserProfileDataService, useValue: userServiceSpy },
        { provide: Router, useValue: routerSpy },
      ],
      imports: [FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should have empty initial values for login properties', () => {
    expect(component.loginUsername).toEqual('');
    expect(component.loginPassword).toEqual('');
  });

  

  it('should handle authentication error on login', () => {
    // Arrange
    authServiceSpy.authenticate.and.returnValue(throwError({ status: 401 }));

    // Act
    component.login();

    // Assert
    expect(authServiceSpy.authenticate).toHaveBeenCalledWith(component.loginUsername, component.loginPassword);
    expect(component.errorMessage).toEqual('Authentication failed. Please check your credentials.');
  });

  it('should call userService.register() on signUp', () => {
    // Arrange
    const mockUserProfile: UserProfile = {
      username: 'testUser',
      firstName: 'John',
      lastName: 'Doe',
      password: 'testPassword',
      dateOfBirth: '1990-01-01',
      email: 'john.doe@example.com',
    };

    userServiceSpy.register.and.returnValue(of(mockUserProfile));

    // Act
    component.signUp();

    // Assert
    expect(userServiceSpy.register).toHaveBeenCalledWith(
      mockUserProfile.username,
      mockUserProfile.password,
      mockUserProfile.email
    );
    expect(component.userProfile).toEqual(mockUserProfile);
    expect(component.successMessage).toEqual('Your Are Register');
    expect(component.errorMessage2).toEqual('');
  });

  it('should handle registration error on signUp', () => {
    // Arrange
    userServiceSpy.register.and.returnValue(throwError({ status: 409 }));

    // Act
    component.signUp();

    // Assert
    expect(userServiceSpy.register).toHaveBeenCalledWith(
      component.username,
      component.password,
      component.email
    );
    expect(component.successMessage).toEqual('');
    expect(component.errorMessage2).toEqual('Username or Email incorrect');
  });


});