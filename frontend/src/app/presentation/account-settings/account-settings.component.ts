import { Component, signal } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ApiResponse, ErrorResponse } from '../../core/types/types';
import { UserDetailResponse } from '../../core/types/accounts.types';
import { AccountsService } from '../../services/accounts.service';
import { HttpErrorResponse } from '@angular/common/http';
import { toast } from 'ngx-sonner';
import { HlmToasterComponent } from '@spartan-ng/ui-sonner-helm';
import {
  HlmTabsComponent,
  HlmTabsContentDirective,
  HlmTabsListComponent,
  HlmTabsTriggerDirective,
} from '../../shared/components/tabs/src';

import { BrnSelectImports } from '@spartan-ng/ui-select-brain';
import { HlmSelectImports } from '../../shared/components/select/src';
import { NotificationComponent } from '../dashboard/notification/notification.component';
import { ProfileIconComponent } from '../dashboard/profile/profile.component';
import { DashboardFooterComponent } from '../dashboard/footer/footer.component';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { LowerCasePipe, TitleCasePipe } from '@angular/common';
@Component({
  selector: 'app-account-settings',
  standalone: true,
  imports: [
    HlmToasterComponent,
    BrnSelectImports,
    HlmSelectImports,
    HlmTabsComponent,
    HlmTabsContentDirective,
    HlmTabsListComponent,
    HlmTabsTriggerDirective,
    NotificationComponent,
    ProfileIconComponent,
    DashboardFooterComponent,
    RouterModule,
    TitleCasePipe,
    LowerCasePipe
  ],
  templateUrl: './account-settings.component.html',
})
export class AccountSettingsComponent {
  myInfo = signal<ApiResponse<UserDetailResponse> | null>(null);
  pageIndex = signal(0);

  pageOption = [
    'general',
    'authentication',
    'teams',
    'invoices',
    'token',
    'domains',
    'activity',
  ];

  constructor(
    private accountService: AccountsService
  ) {}

  ngOnInit(): void {
    switch (window.location.pathname) {
      case '/account-settings/general':
        this.pageIndex.set(0);
        break;
      case '/account-settings/authentication':
        this.pageIndex.set(1);
        break;
      case '/account-settings/invoices':
        this.pageIndex.set(3);
        break;
      default:
    }
    this.getUserData();
  }

  getUserData() {
    this.accountService.userDetail().subscribe({
      next: (res) => {
        if (res.status >= 400) return;
        this.myInfo.set(res.body);
      },
      error: (error: HttpErrorResponse) => {
        let message = (error.error as ErrorResponse).message;
        toast(`Operation Failed`, {
          description: `${message} - status ${status}`,
          action: {
            label: 'Close',
            onClick: () => {},
          },
        });
      },
    });
  }
}
