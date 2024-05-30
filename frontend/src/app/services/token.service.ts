import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  constructor() {}

  setAccessToken(token: string): void {
    localStorage.setItem("access_token", token)
  }

  setRefreshToken(token: string): void {
    localStorage.setItem("refresh_token", token)
  }

  getAccessToken(): string | null {
    return localStorage.getItem("access_token")
  }

  getRefreshToken(): string | null {
    return localStorage.getItem("refresh_token")
  }
}
