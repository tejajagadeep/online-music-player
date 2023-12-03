import { Component } from '@angular/core';
import { AuthenticationService } from '../service/data/authentication.service';
import { UserProfileDataService } from '../service/data/user-profile-data.service';
import { UserProfile } from '../model/UserProfile';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private searchSubscription!: Subscription;
  
  username: string = '';
  password: string = '';
  email: string = '';
  loginEmail: string = '';
  loginPassword: string = '';
  errorMessage!: string
  errorMessage2!: string
  successMessage!: string
  userProfile!: UserProfile;
  constructor(private authService: AuthenticationService, private userService: UserProfileDataService){
  }

  signUp() {
    console.log('Sign up clicked');
    this.userService.register(this.username, this.password, this.email).subscribe({
      next: (v) => {this.userProfile=v, this.successMessage="Your Are Register";
    this.errorMessage2=''},
      error: (e) => {this.errorMessage2 = "User ALready Exists", this.successMessage=''},
      complete: () => console.info('complete') 
    })
    // Add your sign-up logic here using this.userName, this.email, this.password
  }

  login() {
    // Handle the form submission, e.g., send data to the server
    this.authService.authenticate(this.username, this.password).subscribe({
      next: (v) => {localStorage.setItem('token',v.jwt_token);},
      error: (e) => this.errorMessage = 'Authentication failed. Please check your credentials.',
      complete: () => console.info('complete') 
  }
  )
    // Add your logic for authentication or API call here
  }
}
