import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ApiResponse } from '../core/types/types';
import { HttpResponse } from '@angular/common/http';
import { environment } from '../../enviroments/environment.prod';
import { AuthenticateRequestBody, LoginResponse, RefreshToken, RegisterRequestBody, UserDetailResponse } from '../core/types/accounts.types';

@Injectable({
  providedIn: 'root',
})
export class AccountsService {
  constructor(private apiService: ApiService) {}

  authenticate = ({
    username,
    password,
  }: AuthenticateRequestBody) => {
    return this.apiService.post<HttpResponse<LoginResponse>>(
      `${environment.accountService}/auth/login`,
      { username, password },
      { observe: 'events' },
    );
  };

  register = ({
    username,
    email,
    password,
  }: RegisterRequestBody) => {
    return this.apiService.post<HttpResponse<void>>(
      `${environment.accountService}/auth/register`,
      { email, username, password },
      { observe: 'events' },
    );
  };

  refreshToken = (refreshToken: RefreshToken) => {
    return this.apiService.post<HttpResponse<LoginResponse>>('/api/v1/account/refresh', refreshToken, {
      observe: 'events',
    });
  };

  userDetail = () => {
    return this.apiService.get<HttpResponse<ApiResponse<UserDetailResponse>>>(
      `${environment.accountService}/user`,
      { observe: 'events' },
    );
  };

  validateMagiclink = (magiclink: string) => {
    return this.apiService.post<HttpResponse<LoginResponse>>(
      `${environment.accountService}/auth/magiclink/verify/${magiclink}`,
      {},
      { observe: 'events' },
    );
  }
  
}
