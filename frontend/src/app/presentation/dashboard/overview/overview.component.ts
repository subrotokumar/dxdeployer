import { Component, OnInit, signal } from '@angular/core';
import { ProjectService } from '../../../services/project.service';
import {
  HlmCardContentDirective,
  HlmCardDescriptionDirective,
  HlmCardDirective,
  HlmCardFooterDirective,
  HlmCardHeaderDirective,
  HlmCardTitleDirective,
} from '../../../shared/components/ui-card-helm/src';
import { HlmBadgeDirective } from '../../../shared/components/ui-badge-helm/src';
import { BrnSelectImports } from '@spartan-ng/ui-select-brain';
import { HlmSelectImports } from '../../../shared/components/ui-select-helm/src';
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
} from '../../../shared/components/ui-menu-helm/src';
import { HlmIconComponent } from '../../../shared/components/ui-icon-helm/src';
import { Project } from '../../../core/types/project.types';
import { HttpErrorResponse } from '@angular/common/http';
import { toast } from 'ngx-sonner';
import { HlmToasterComponent } from '@spartan-ng/ui-sonner-helm';
import { ErrorResponse } from '../../../core/types/types';
import { DashboardFooterComponent } from '../footer/footer.component';
import { lucideLayoutGrid } from '@ng-icons/lucide';

@Component({
  selector: 'overview',
  standalone: true,
  imports: [
    HlmCardContentDirective,
    HlmCardDescriptionDirective,
    HlmCardDirective,
    HlmCardFooterDirective,
    HlmCardHeaderDirective,
    HlmCardTitleDirective,
    HlmBadgeDirective,
    HlmSelectImports,
    BrnSelectImports,
    HlmMenuComponent,
    BrnMenuTriggerDirective,
    HlmMenuGroupComponent,
    HlmMenuItemDirective,
    HlmMenuItemIconDirective,
    HlmMenuItemSubIndicatorComponent,
    HlmMenuLabelComponent,
    HlmMenuSeparatorComponent,
    HlmMenuShortcutComponent,
    HlmSubMenuComponent,
    HlmIconComponent,
    HlmToasterComponent,
    DashboardFooterComponent
  ],
  templateUrl: './overview.component.html',
})
export class OverviewComponent implements OnInit {
  projects = signal<Project[]>([]);

  gridView = signal(false)

  viewStyle(num:number){
  }

  constructor(private projectService: ProjectService) {
    console.log("Overiew")
  }

  ngOnInit(): void {
    this.fetchProjects()
  }

  fetchProjects() {
    this.projectService.findAllProjects().subscribe({
      next: (res) => {
        console.log(res)
        if(res.status>=400){
          return;
        }
        this.projects.set(res.body?.data??[])
      },
      error: (err) => {
        console.log(err)
        this.showToast((err.error as ErrorResponse).message)
      }
    })
  }

  showToast(msg: string) {
    toast('Event has been created', {
      description: msg,
      action: {
        label: 'Close',
        onClick: () => {},
      }
    })
  }
}
