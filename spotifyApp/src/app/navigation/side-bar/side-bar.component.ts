import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/service/data/authentication.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  username!: string;

  constructor(private authService: AuthenticationService) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('authenticatedUser')+'';
    this.verifyToken();
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

}
