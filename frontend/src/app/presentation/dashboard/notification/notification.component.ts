import { Component } from '@angular/core';


import { BrnSheetContentDirective, BrnSheetTriggerDirective } from '@spartan-ng/ui-sheet-brain';
import { HlmSheetComponent } from '../../../shared/components/sheet/src/lib/hlm-sheet.component';
import { HlmSheetContentComponent } from '../../../shared/components/sheet/src/lib/hlm-sheet-content.component';
import { HlmSheetDescriptionDirective } from '../../../shared/components/sheet/src/lib/hlm-sheet-description.directive';
import { HlmSheetFooterComponent } from '../../../shared/components/sheet/src/lib/hlm-sheet-footer.component';
import { HlmSheetHeaderComponent } from '../../../shared/components/sheet/src/lib/hlm-sheet-header.component';
import { HlmSheetTitleDirective } from '../../../shared/components/sheet/src/lib/hlm-sheet-title.directive';

import { HlmSkeletonComponent } from '../../../shared/components/skeleton/src';

@Component({
  selector: 'notification',
  standalone: true,
  imports: [
    BrnSheetContentDirective, 
    BrnSheetTriggerDirective,
    HlmSheetComponent,
    HlmSheetContentComponent,
    HlmSheetDescriptionDirective,
    HlmSheetFooterComponent,
    HlmSheetHeaderComponent,
    HlmSheetTitleDirective,
    HlmSkeletonComponent
  ],
  templateUrl: './notification.component.html',
})
export class NotificationComponent {
  notification = [1,2,3,4,5,6]
}
