import { Component } from '@angular/core';
import { AuthenticationService } from '../service/data/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  email: string = '';
  loginEmail: string = '';
  loginPassword: string = '';
  constructor(private authService: AuthenticationService){

  }

  signUp() {
    console.log('Sign up clicked');
    // Add your sign-up logic here using this.userName, this.email, this.password
  }

  login() {
    console.log('Login clicked');
    // Add your login logic here using this.loginEmail and this.loginPassword
  }

  onSubmit() {
    // Handle the form submission, e.g., send data to the server
    console.log('Username:', this.username);
    console.log('Password:', this.password);
    this.authService.authenticate(this.username, this.password).subscribe({
      next: (v) => {localStorage.setItem('token',v.jwtToken);},
      error: (e) => console.error(e),
      complete: () => console.info('complete') 
  }
  )
    // Add your logic for authentication or API call here
  }
}
