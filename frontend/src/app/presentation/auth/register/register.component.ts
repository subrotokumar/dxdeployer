import { Component, OnInit, Signal, computed } from '@angular/core';
import { FormControl, FormGroup, PatternValidator, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PASSWORD_REGEX } from '../../../core/constants/constants';
import { AccountsService } from '../../../services/accounts.service';
import { Assets } from '../../../core/constants/assets';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ErrorResponse } from '../../../core/types/types';
import { toast } from 'ngx-sonner';
import { HlmToasterComponent } from '@spartan-ng/ui-sonner-helm';
import { DashboardFooterComponent } from '../../dashboard/footer/footer.component';

@Component({
  selector: 'RegisterScreen',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, HlmToasterComponent, DashboardFooterComponent ],
  providers: [AccountsService],
  templateUrl: './register.component.html',
})
export class RegisterScreen implements OnInit  {

  constructor(
    private accountServcie: AccountsService
  ){}

  ratingEarth = Assets.Gif.rotatingEarth;

  username = new FormControl("", [
    Validators.required,
    Validators.minLength(6)
  ])

  email = new FormControl("",[
    Validators.required,
    Validators.email
  ])

  password = new FormControl("", [
    Validators.required,
    Validators.minLength(6),
    // Validators.pattern(PASSWORD_REGEX)
  ])

  confirmPassword = new FormControl("", [
    Validators.required,
    Validators.minLength(6),
    // Validators.pattern(PASSWORD_REGEX)
  ])

  acceptTerms = new FormControl(false, [
    Validators.required,
    Validators.requiredTrue
  ])

  btnActive: Signal<boolean> = computed(()=> {
    return this.email.valid 
      && this.confirmPassword.valid
      && this.password.valid
      && this.confirmPassword.valid
      && this.acceptTerms.valid;
  });

  registerForm = new FormGroup({
    username: this.username,
    email: this.email,
    password: this.password,
    confirmPassword: this.confirmPassword,
    acceptTerms: this.acceptTerms,
  })

  ngOnInit(): void {
    console.log("Register Page ", this.btnActive());

  }

  registerUser() {
    if(!this.registerForm.valid){
      toast(`Form Validation Error`, {
        description: `Please fill the information correctly`,
        action: {
          label: 'Close',
          onClick: () => {},
        }
      })
      return;
    }
    const response = this.accountServcie.register({
      email:  this.registerForm.value.email ?? '',
      password: this.registerForm.value.email ?? '',
      username: this.registerForm.value.username ?? ''
    })
    response.subscribe({
      next: (res: HttpResponse<void>) => {
        if(res.status<400){
          toast(`Registration Successfull!`, {
            description: `Welcome to DxDeployer`,
            action: {
              label: 'Close',
              onClick: () => {},
            }
          })
          return;
        }
      },
      error: (error: HttpErrorResponse) => {
        let message = (error.error as ErrorResponse).message
        toast(`Registration Failed!`, {
          description: message,
          action: {
            label: 'Close',
            onClick: () => {},
          }
        })
        return;
      }
    })
  }

  reset() {
    this.registerForm.reset()
  }
}
