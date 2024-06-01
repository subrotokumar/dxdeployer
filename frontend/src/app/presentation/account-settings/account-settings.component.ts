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
    ProfileIconComponent
  ],
  templateUrl: './account-settings.component.html',
})
export class AccountSettingsComponent {
  myInfo= signal<ApiResponse<UserDetailResponse>|null>(null);

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
