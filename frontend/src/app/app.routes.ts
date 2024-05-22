import { Routes } from '@angular/router';
import { LoginScreen } from './features/auth/login/login.component';
import { RegisterScreen } from './features/auth/register/register.component';
import { HomeComponent } from './features/home/home.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginScreen },
  { path: 'register', component: RegisterScreen },
];
