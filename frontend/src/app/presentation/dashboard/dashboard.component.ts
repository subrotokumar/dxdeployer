import { Component, Signal, signal } from '@angular/core';
import { AccountsService } from '../../services/accounts.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ApiResponse, ErrorResponse, UserDetailResponse } from '../../core/types';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTab, MatTabGroup } from '@angular/material/tabs';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [MatTab, MatTabGroup],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {
  myInfo= signal<string>("");

  primary = "white"

  constructor(private accountService: AccountsService, private snackBar: MatSnackBar){}

  getUserData(){
    this.accountService.userDetail().subscribe({
      next: (res) => {
        if(res.status>=400) return;
        this.myInfo.set(JSON.stringify(res.body?.data));
      },
      error: (error: HttpErrorResponse) => {
        let message = (error.error as ErrorResponse).message
        this.snackBar.open(message, 'Close', {verticalPosition: 'top'});
      }
    })
  }
}
