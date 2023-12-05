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

  constructor(private userProfileService: UserProfileDataService, private fb: FormBuilder) {
    // this.userProfileForm = this.fb.group({
    //   firstName: ['', Validators.required],
    //   lastName: ['', Validators.required],
    //   dateOfBirth: [null, Validators.required],
    //   email: ['', [Validators.required, Validators.email]],
    // });
  }

  profileForm = this.fb.group({
    firstName:[''],
    lastName:[''],
    email:[''],
    dateOfBirth:[''],
    gender:['']
  });
 
  saveForm(){
    console.log('Form data is ', this.profileForm.value);
  }

  ngOnInit(): void {
    this.getUserProfile()
  }
  
  getUserProfile(){
    this.userProfileService.getUserById().subscribe({
      next: (v) => {
        this.profileForm.value.firstName = v.firstName;
        this.profileForm.value.lastName = v.lastName;
        this.profileForm.value.email = v.email;
        this.profileForm.value.gender = v.gender;
      },
      error: (e) => {console.error('e')},
      complete: () => console.info('complete')
    });

    console.log(this.profileForm.value)
  }

}
