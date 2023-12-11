import { Component, OnInit } from '@angular/core';
import { isChecker } from 'src/app/model/isChecker';
import { AuthenticationService } from 'src/app/service/data/authentication.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  username!: string;
  isC!: isChecker;
  constructor(private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.username = localStorage.getItem('authenticatedUser') + '';
    this.verifyToken();
  }

  verifyToken() {
    const isCheck: Map<string, string> = new Map<string, string>();
    isCheck.set(this.username, 'User');
    this.isC = new isChecker();
    const token = localStorage.getItem('token') + '';
    this.authService.validateToken(token).subscribe({
      next: (v) => {
        this.isC = v;
        console.log('conso')
        console.log(this.isC)
      },
      error: (e) => { console.error(e); localStorage.removeItem('token'), localStorage.removeItem('authenticatedUser') },
      complete: () => console.info('complete')
    });
  }

}
