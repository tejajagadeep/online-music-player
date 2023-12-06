import { Component } from '@angular/core';
import { AuthenticationService } from '../service/data/authentication.service';
import { UserProfileDataService } from '../service/data/user-profile-data.service';
import { UserProfile } from '../model/UserProfile';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

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
  loginUsername: string = '';
  loginPassword: string = '';
  errorMessage!: string
  errorMessage2!: string
  successMessage!: string
  userProfile!: UserProfile;
  constructor(private router: Router, private authService: AuthenticationService, private userService: UserProfileDataService){
  }

  signUp() {
    console.log('Sign up clicked');
    this.userService.register(this.username, this.password, this.email).subscribe({
      next: (v) => {this.userProfile=v, this.successMessage="Your Are Register";
    this.errorMessage2=''},
      error: (e) => {
      this.successMessage='';
      if (e.status === 409){
        this.errorMessage2 = "Username or Email incorrect";
      }
      if (e.status === 503){
        this.errorMessage2 = "Service Error"
      }
    },
      complete: () => console.info('complete') 
    })
    // Add your sign-up logic here using this.userName, this.email, this.password
  }

  login() {
    // Handle the form submission, e.g., send data to the server
    this.authService.authenticate(this.loginUsername, this.loginPassword).subscribe({
      next: (v) => {localStorage.setItem('token',v.jwt_token);
      this.router.navigate(['/home']);
    },
      error: (e) => this.errorMessage = 'Authentication failed. Please check your credentials.',
      complete: () => console.info('complete') 
  }
  )
    // Add your logic for authentication or API call here
  }
}
