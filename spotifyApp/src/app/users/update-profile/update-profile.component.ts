import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserProfile } from 'src/app/model/UserProfile';
import { UserProfileDataService } from 'src/app/service/data/user-profile-data.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  userProfile!: UserProfile;

  emailExists: boolean = false;

  constructor(private userProfileService: UserProfileDataService, private fb: FormBuilder) {
    
  }

  profileForm = this.fb.group({
    firstName:[''],
    lastName:[''],
    email:[''],
    dateOfBirth:[''],
    // gender:['']
  });
 
  saveForm(){
    console.log('Form data is ', this.profileForm.value);
    
    this.userProfileService.updateuser(this.profileForm.value).subscribe({
      next: (v) => {
        console.log(v.email)
      },
      error: (e) => {console.error('e')},
      complete: () => console.info('complete')
    });
  }

  ngOnInit(): void {
    this.getUserProfile()
    // Disable the email input field
    

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
      error: (e) => {console.error('e')},
      complete: () => console.info('complete')
    });

    console.log(this.profileForm.value)
  }

  

}