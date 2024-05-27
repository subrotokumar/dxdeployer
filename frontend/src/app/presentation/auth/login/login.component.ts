import { Component, OnDestroy, OnInit, computed, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterModule } from '@angular/router';
import { AccountsService } from '../../../services/accounts.service';
import { ErrorResponse } from '../../../core/types/types';

@Component({
  selector: 'LoginScreen',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
})
export class LoginScreen implements OnDestroy {

  constructor(
    private snackBar: MatSnackBar,
    private accountService: AccountsService,
    private router: Router,
  ){}

  ngOnDestroy(): void {
  }

  mainAuthScreen = signal(true);

  emailAuth() {
    this.mainAuthScreen.set(false);
  }

  mainAuth() {
    this.mainAuthScreen.set(true);
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

  loginWithUserPassword() {
    if(!this.loginGroup.valid){
      this.snackBar.open("Please enter valid email and password", 'Close', {verticalPosition: 'top'});
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
          var message = (err.error as ErrorResponse).message ?? 'Something went wrong';
          this.snackBar.open(message, 'Close', {verticalPosition: 'top'});
      },
      complete: () => {
        this.router.navigate(['/dashboard'])
      }
    })
  }

}
