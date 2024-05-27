import { Component, OnInit, Signal, signal } from '@angular/core';
import { AccountsService } from '../../services/accounts.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ApiResponse, ErrorResponse, UserDetailResponse } from '../../core/types/types';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTab, MatTabGroup } from '@angular/material/tabs';
import {
  HlmTabsComponent,
  HlmTabsContentDirective,
  HlmTabsListComponent,
  HlmTabsTriggerDirective,
} from '../../shared/components/ui-tabs-helm/src';

import { BrnSelectImports } from '@spartan-ng/ui-select-brain';
import { HlmSelectImports } from '../../shared/components/ui-select-helm/src';
import { OverviewComponent } from './overview/overview.component';

import { BrnSheetContentDirective, BrnSheetTriggerDirective } from '@spartan-ng/ui-sheet-brain';
import {
  HlmSheetComponent,
  HlmSheetContentComponent,
  HlmSheetDescriptionDirective,
  HlmSheetFooterComponent,
  HlmSheetHeaderComponent,
  HlmSheetTitleDirective,
} from '../../shared/components/ui-sheet-helm/src';
import { DashboardFooterComponent } from './footer/footer.component';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    MatTab, 
    MatTabGroup,
    HlmTabsComponent,
    HlmTabsContentDirective,
    HlmTabsListComponent,
    HlmTabsTriggerDirective,
    BrnSelectImports,
    HlmSelectImports,
    OverviewComponent,
    BrnSheetContentDirective, 
    BrnSheetTriggerDirective,
    HlmSheetComponent,
    HlmSheetContentComponent,
    HlmSheetDescriptionDirective,
    HlmSheetFooterComponent,
    HlmSheetHeaderComponent,
    HlmSheetTitleDirective,
    DashboardFooterComponent
  ],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit{
  myInfo= signal<ApiResponse<UserDetailResponse>|null>(null);

  primary = "white"

  constructor(private accountService: AccountsService, private snackBar: MatSnackBar){}

  ngOnInit(): void {
    this.getUserData();
  }

  getUserData(){
    this.accountService.userDetail().subscribe({
      next: (res) => {
        if(res.status>=400) return;
        this.myInfo.set(res.body);
      },
      error: (error: HttpErrorResponse) => {
        let message = (error.error as ErrorResponse).message
        this.snackBar.open(message, 'Close', {verticalPosition: 'top'});
      }
    })
  }
}
