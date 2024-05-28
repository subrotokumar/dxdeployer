import { HttpContext, HttpHeaders, HttpParams } from '@angular/common/http';
import internal from 'stream';

export interface Options {
  headers?:
    | HttpHeaders
    | {
        [header: string]: string | string[];
      };
  observe: 'events';
  context?: HttpContext;
  params?:
    | HttpParams
    | {
        [param: string]:
          | string
          | number
          | boolean
          | ReadonlyArray<string | number | boolean>;
      };
  reportProgress?: boolean;
  responseType?: 'json';
  withCredentials?: boolean;
  transferCache?:
    | {
        includeHeaders?: string[];
      }
    | boolean;
}

export interface PostOptions extends Options {}

export interface DeleteOptions {
  headers?:
    | HttpHeaders
    | {
        [header: string]: string | string[];
      };
  observe: 'events';
  context?: HttpContext;
  params?:
    | HttpParams
    | {
        [param: string]:
          | string
          | number
          | boolean
          | (string | number | boolean)[];
      };
  reportProgress?: boolean;
  responseType?: 'json';
  withCredentials?: boolean;
  body?: any | null;
}


export interface ErrorResponse {
  path: string;
  message: string;
  statusMessage: string;
  statusCode: number;
  time: string;
}



export interface ApiResponse<T> {
  data: T;
  code: number;
  message: string;
}

