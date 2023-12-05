import { Injectable, inject } from '@angular/core';
import { AuthenticationService } from '../data/authentication.service';
import { AuthAccessToken } from 'src/app/model/AuthAccessToken';
import { ActivatedRouteSnapshot, CanActivateFn, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, map, take } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthGuardService { 

}

export const AuthGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('token');
  const router = inject(Router);
  if (token) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
