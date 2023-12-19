import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { UserProfile } from 'src/app/model/UserProfile';
import { AuthenticationService } from 'src/app/service/data/authentication.service';
import { UserProfileDataService } from 'src/app/service/data/user-profile-data.service';

export function hasNumberValidator(control: AbstractControl): ValidationErrors | null {
  const hasNumber = /\d/.test(control.value);
  return hasNumber ? null : { hasNumber: true };
}
@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  userProfile!: UserProfile;
  isNumber = false;
  isNumber2 = false;
  emailExists: boolean = false;
  message='';
  errorA!: string;
  errorMessage=true;

  constructor(private authService: AuthenticationService, private userProfileService: UserProfileDataService, private fb: FormBuilder) {
    
  }

  profileForm = this.fb.group({
    firstName:['', Validators.required, hasNumberValidator],
    lastName:[''],
    email:[''],
    dateOfBirth:[''],
    // gender:['']
  });

  noNumbersValidator() {
    return (control: FormControl) => {
      const hasNumber = /\d/.test(control.value);
      return hasNumber ? { hasNumber: true } : null;
    };
  }
 
  saveForm(){
    console.log('Form data is ', this.profileForm.value);
    
    this.userProfileService.updateuser(this.profileForm.value).subscribe({
      next: (v) => {
        console.log(v.email)
      },
      error: (e) => {console.error('e')},
      complete: () => {this.message = "Details updated"}
    });
  }

  isnumberValidatorCheck(): boolean {
    return true
  }

  OnlyAlbhabets(event: any): boolean {

    const charCode = (event.which) ? event.which : event.keyCode;

    if (charCode == 32 || ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))) {
      this.isNumber = false;
      return true
    }

    this.errorA = 'Please enter only letters and spaces.'; 
    this.isNumber = true;
    return false;

  }

  OnlyAlbhabets2(event: any): boolean {

    const charCode = (event.which) ? event.which : event.keyCode;

    if (charCode == 32 || ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))) {
      this.isNumber2 = false;
      return true
    }

    this.errorA = 'Please enter only letters and spaces.'; 
    this.isNumber2 = true;
    return false;

  }
  ngOnInit(): void {
    this.getUserProfile()
    
  }

  verifyToken(){
    const token = localStorage.getItem('token')+'';
    this.authService.validateToken(token).subscribe({
      next: (v) => {
        console.log(v);
      },
      error: (e) => {console.error(e); localStorage.removeItem('token'), localStorage.removeItem('authenticatedUser')},
      complete: () => console.info('complete')
    });
  }
  
  getUserProfile(){
    this.userProfileService.getUserById().subscribe({
      next: (v) => {
        console.log(v)
        
        this.profileForm = this.fb.group({
          firstName:v.firstName,
          lastName:v.lastName,
          email:v.email,
          dateOfBirth:v.dateOfBirth,
          // gender:v.gender
        });

      },
      error: (e) => {console.error('e'), this.errorMessage=false},
      complete: () => console.info('complete')
    });

    console.log(this.profileForm.value)
  }

  

}
