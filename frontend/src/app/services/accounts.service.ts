import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { register } from 'module';
import { ApiResponse, LoginResponse, UserDetailResponse } from '../core/types/types';
import { HttpResponse } from '@angular/common/http';
import { environment } from '../../enviroments/environment.prod';

interface AuthenticateRequestBody {
  username: string;
  password: string;
}

interface RegisterRequestBody {
  username: string;
  email: string;
  password: string;
}

interface RefreshToken {
  refreshToken: string;
}

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
    const accessToken = localStorage.getItem('access_token');
    return this.apiService.get<HttpResponse<ApiResponse<UserDetailResponse>>>(
      '/api/v1/account/user',
      {
        observe: 'events',
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      },
    );
  };
}
