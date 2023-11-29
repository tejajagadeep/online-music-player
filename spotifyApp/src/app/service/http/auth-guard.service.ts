import { Injectable, inject } from '@angular/core';
import { AuthenticationService } from '../data/authentication.service';
import { AuthAccessToken } from 'src/app/model/AuthAccessToken';
import { ActivatedRouteSnapshot, CanActivateFn, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, map, take } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthGuardService { 

  isActivated: boolean;

  constructor(private authService: AuthenticationService, private router: Router){
    this.isActivated = this.canActivate();
  }
  canActivate(): boolean {
    if (this.authService.authAccessToken.jwtToken === localStorage.getItem('authAccessToken')) {
      return true;
    } else {
      this.router.navigate(['/login']);
    return false;
    }
  }

}

// export const canActivateTeam: CanActivateFn =
//   (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
//     return isValue;
// };
export const canActivateTeam: CanActivateFn = (route, state) => {
  return inject(AuthGuardService).canActivate();
};

// function authGuardFactory(tokenService: AuthenticationService, router: Router): CanActivateFn {
//   return (
//     next: ActivatedRouteSnapshot,
//     state: RouterStateSnapshot
//   ) => {
//     return tokenService.authenticate.pipe (
//       take(1),
//       map((status) => {
//         console.log('auth status: ', status);
//         if (!status) {
//           return router.createUrlTree(['/login']);
//         }
//         return true;
//       })
//     );
//   };
// }