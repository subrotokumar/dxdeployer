import { Component, OnInit, Signal, computed } from '@angular/core';
import { FormControl, FormGroup, PatternValidator, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PASSWORD_REGEX } from '../../../core/constants';
import { AccountsService } from '../../../services/accounts.service';
import { Assets } from '../../../core/assets';
import {MatSnackBar} from "@angular/material/snack-bar"
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ErrorResponse } from '../../../core/types';

@Component({
  selector: 'RegisterScreen',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  providers: [AccountsService],
  templateUrl: './register.component.html',
})
export class RegisterScreen implements OnInit  {

  constructor(
    private accountServcie: AccountsService,
    private snackBar: MatSnackBar
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
      this.snackBar.open("Please fill the information correctly", 'Close', {verticalPosition: 'top'});
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
          this.snackBar.open("Registration Successfull!", "Close", {verticalPosition: 'top'});
          return;
        }
      },
      error: (error: HttpErrorResponse) => {
        let message = (error.error as ErrorResponse).message
        this.snackBar.open(message, 'Close', {verticalPosition: 'top'});
      }
    })
  }

  reset() {
    this.registerForm.reset()
  }
}
