import { Component, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AccountsService } from '../../../services/accounts.service';
import { HttpErrorResponse } from '@angular/common/http';
import { toast } from 'ngx-sonner';
import { HlmToasterComponent } from '@spartan-ng/ui-sonner-helm';
@Component({
  selector: 'magiclink',
  standalone: true,
  imports: [
    HlmToasterComponent,
  ],
  templateUrl: './magiclink.component.html'
})
export class MagiclinkComponent implements OnInit{
  magiclink = signal("")

  isLoading = signal(true);

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountsService
  ) {}

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        this.magiclink.set(params['magiclink'])
      }
    );
    this.validateMagiclink()
  }

  validateMagiclink() {
    this.accountService.validateMagiclink(this.magiclink()).subscribe({
      next: (response) => {
        if(response.status>=400) return;
        toast(`Login Successful`, {
          description: `Welcome to DxDeployer`,
          action: {
            label: 'Close',
            onClick: () => {},
          }
        })
        localStorage.setItem("access_token", response.body?.accessToken.token??'')
      },
      error: (err: HttpErrorResponse) => {
        // const error = err.error as ErrorResponse;
        console.log(err)
        // toast(`Login Unsuccessful`, {
        //   description: error.message,
        //   action: {
        //     label: 'Close',
        //     onClick: () => {},
        //   }
        // })
      },
    })
  }
}
