import { Component, OnInit } from '@angular/core';
import { UserProfile } from 'src/app/model/UserProfile';
import { UserProfileDataService } from 'src/app/service/data/user-profile-data.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  userProfile!: UserProfile;

  constructor(private userProfileService: UserProfileDataService) {}

  ngOnInit(): void {
    this.getUserProfile()
  }

  getUserProfile(){
    this.userProfileService.getUserById().subscribe({
      next: (v) => {
        this.userProfile = v;
      },
      error: (e) => {console.error('e')},
      complete: () => console.info('complete')
    });
  }
}
