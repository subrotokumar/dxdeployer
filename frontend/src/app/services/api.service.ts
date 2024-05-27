import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeleteOptions, Options, PostOptions } from '../core/types/types';
import { throws } from 'assert';
import { environment } from '../../enviroments/environment.prod';

@Injectable({
  providedIn: 'root',
})
export class ApiService {

  private url = environment.domain

  constructor(private http: HttpClient) {}

  get<T>(endpoint: string, options: Options): Observable<T> {
    return this.http.get<T>(this.url+endpoint, options) as Observable<T>;
  }

  post<T>(endpoint:string, body: any | null, options: PostOptions) : Observable<T> {
    return this.http.post<T>(this.url+endpoint, body, options) as Observable<T>;
  }

  delete<T>(endpoint: string, options: DeleteOptions): Observable<T> {
    return this.http.delete<T>(this.url+endpoint, options) as Observable<T>;
  }

  patch<T>(endpoint: string, options: Options): Observable<T> {
    return this.http.patch<T>(this.url+endpoint, options) as Observable<T>;
  }
}

