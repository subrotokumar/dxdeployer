import { Injectable, signal } from '@angular/core';
import { ApiResponse, ErrorResponse } from '../core/types/types';
import { UserDetailResponse } from '../core/types/accounts.types';
import { AccountsService } from './accounts.service';
import { HttpErrorResponse } from '@angular/common/http';
import { toast } from 'ngx-sonner';

@Injectable({
  providedIn: 'root'
})
export class ProfileServiceService  {
  myInfo= signal<ApiResponse<UserDetailResponse>|null>(null);

  constructor(
    private accountService: AccountsService
  ) { }

 fetch(){
    this.accountService.userDetail().subscribe({
      next: (res) => {
        if(res.status>=400) return;
        this.myInfo.set(res.body);
      },
      error: (error: HttpErrorResponse) => {
        let message = (error.error as ErrorResponse).message
        toast(`Operation Failed`, {
          description: `${message} - status ${status}`,
          action: {
            label: 'Close',
            onClick: () => {},
          }
        })
      }
    })
  }


}
