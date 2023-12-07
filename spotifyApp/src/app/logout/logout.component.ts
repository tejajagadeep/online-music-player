import { Component, NgModule, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
})


export class LogoutComponent implements OnInit{

  ngOnInit(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('authenticatedUser');
  }

}
