import { Component, OnDestroy, OnInit, computed, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AccountsService } from '../../../services/accounts.service';
import { ErrorResponse } from '../../../core/types/types';
import { toast } from 'ngx-sonner';
import { HlmToasterComponent } from '@spartan-ng/ui-sonner-helm';

enum Page {
  MAIN,
  EMAIL,
  MAGICLINK
}

@Component({
  selector: 'LoginScreen',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, HlmToasterComponent],
  templateUrl: './login.component.html',
})
export class LoginScreen implements OnDestroy {

  showPassword = signal(false);
  toggleShowPassword() {
    this.showPassword.update(v => !v)
  }

  constructor(
    private accountService: AccountsService,
    private router: Router,
  ){}

  ngOnDestroy(): void {
  }

  mainAuthScreen = signal(Page.MAIN);

  emailAuth() {
    this.mainAuthScreen.set(Page.EMAIL);
  }

  mainAuth() {
    this.mainAuthScreen.set(Page.MAIN);
  }

  magiclinkAuth() {
    this.mainAuthScreen.set(Page.MAGICLINK);
  }
  
  

  usernameController = new FormControl("",[
    Validators.required
  ])

  passwordController = new FormControl("",[
    Validators.minLength(6),
    Validators.required
  ])

  loginGroup = new FormGroup({
    username: this.usernameController,
    password: this.passwordController,
  })

  magiclinkForm = new FormGroup({
    username: this.usernameController,
  })

  loginWithUserPassword() {
    if(!this.loginGroup.valid){
      toast(`User login failed`, {
        description: `Please enter valid email and password`,
        action: {
          label: 'Close',
          onClick: () => {},
        }
      })
      return;
    }
    this.accountService.authenticate({
      username: this.loginGroup.value.username??'',
      password: this.loginGroup.value.password??'',
    }).subscribe({
      next: (response) => {
          if(response.status>=400) return;
          const accessToken = response.body?.accessToken.token;
          const refreshToken = response.body?.refreshToken.token;
          if(accessToken) localStorage.setItem("access_token", accessToken);
          if(refreshToken) localStorage.setItem("refresh_token", refreshToken)
      },
      error: (err) => {
          let status = (err.error as ErrorResponse).statusCode;
          let message = (err.error as ErrorResponse).message ?? 'Something went wrong';
          toast(`User login failed`, {
            description: `${message} - status ${status ?? 500}`,
            action: {
              label: 'Close',
              onClick: () => {},
            }
          })
      },
      complete: () => {
        this.router.navigate(['/dashboard'])
      }
    })
  }

  loginWithMagiclink() {
    if(!this.magiclinkForm.valid){
      toast(`User login failed`, {
        description: `Please enter valid username or email`,
        action: {
          label: 'Close',
          onClick: () => {},
        }
      })
      return;
    }
    this.accountService
    .loginWithMagiclink(this.magiclinkForm.value.username ?? '').subscribe({
      next: (response) => {
          if(response.status>=400) return;
      },
      error: (err) => {
          let status = (err.error as ErrorResponse).statusCode;
          let message = (err.error as ErrorResponse).message ?? 'Something went wrong';
          toast(`User login failed`, {
            description: `${message} - status ${status ?? 500}`,
            action: {
              label: 'Close',
              onClick: () => {},
            }
          })
      },
      complete: () => {
        toast(`Magiclink send to email`, {
          description: `Please check you email inbox`,
          action: {
            label: 'Close',
            onClick: () => {},
          }
        })
      }
    })
  }
}
