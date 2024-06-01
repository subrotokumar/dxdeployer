import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { LoginResponse } from '../core/types/accounts.types';
import { Route, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private tokenService: TokenService,
    private router: Router
  ) { }

  login(data: LoginResponse){
    localStorage.setItem("access_token", data.accessToken.token);
    localStorage.setItem("access_token_expiry", data.accessToken.expiry)
    localStorage.setItem("refresh_token", data.refreshToken.token);
    localStorage.setItem("refresh_token_expiry", data.refreshToken.expiry)
  }

  logout(){
    localStorage.clear()
    this.router.navigateByUrl("/home")
  }


}
