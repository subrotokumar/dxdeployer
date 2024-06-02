import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { BrnMenuTriggerDirective } from '@spartan-ng/ui-menu-brain';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent,
  HlmMenuItemDirective,
  HlmMenuItemIconDirective,
  HlmMenuItemSubIndicatorComponent,
  HlmMenuLabelComponent,
  HlmMenuSeparatorComponent,
  HlmMenuShortcutComponent,
  HlmSubMenuComponent,
} from '../../../shared/components/menu/src';
@Component({
  selector: 'profile-icon',
  standalone: true,
  imports: [
    HlmMenuComponent,
    HlmMenuGroupComponent,
    HlmMenuItemDirective,
    HlmMenuItemIconDirective,
    HlmMenuItemSubIndicatorComponent,
    HlmMenuLabelComponent,
    HlmMenuSeparatorComponent,
    HlmMenuShortcutComponent,
    HlmSubMenuComponent,
    BrnMenuTriggerDirective,
    RouterModule
  ],
  templateUrl: './profile.component.html',
})
export class ProfileIconComponent {
  constructor(private router: Router){}
  logout() {
    localStorage.removeItem("access_token")
    this.router.navigate(["/home"])
  }
}
