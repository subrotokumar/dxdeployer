import { Component, OnInit, Signal, signal } from '@angular/core';
import { AccountsService } from '../../services/accounts.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ApiResponse, ErrorResponse } from '../../core/types/types';
import {
  HlmTabsComponent,
  HlmTabsContentDirective,
  HlmTabsListComponent,
  HlmTabsTriggerDirective,
} from '../../shared/components/tabs/src';

import { BrnSelectImports } from '@spartan-ng/ui-select-brain';
import { HlmSelectImports } from '../../shared/components/select/src';
import { OverviewComponent } from './overview/overview.component';
import { DashboardFooterComponent } from './footer/footer.component';
import { NotificationComponent } from './notification/notification.component';
import { ProfileIconComponent } from './profile/profile.component';
import { UserDetailResponse } from '../../core/types/accounts.types';
import { toast } from 'ngx-sonner';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    HlmTabsComponent,
    HlmTabsContentDirective,
    HlmTabsListComponent,
    HlmTabsTriggerDirective,
    BrnSelectImports,
    HlmSelectImports,
    OverviewComponent,
    DashboardFooterComponent,
    NotificationComponent,
    ProfileIconComponent
  ],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit{
  myInfo= signal<ApiResponse<UserDetailResponse>|null>(null);

  primary = "white"

  constructor(private accountService: AccountsService){}

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
