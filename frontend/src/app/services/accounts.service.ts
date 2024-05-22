import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AccountsService {
  constructor(private apiService: ApiService) {}

  authenticate = (user: string, params: any): Observable<any> => {
    return this.apiService.get(url, params);
  };

  register = ()
}
