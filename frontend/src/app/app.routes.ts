import { Routes } from '@angular/router';
import { HomeComponent } from './presentation/home/home.component';
import { LoginScreen } from './presentation/auth/login/login.component';
import { RegisterScreen } from './presentation/auth/register/register.component';
import { DashboardComponent } from './presentation/dashboard/dashboard.component';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { 
    path: '', 
    redirectTo: 'home', 
    pathMatch: 'full' 
  },
  { 
    path: 'home', 
    component: HomeComponent
  },
  { 
    path: 'login', 
    component: LoginScreen
  },
  { 
    path: 'register', 
    component: RegisterScreen 
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [ authGuard ]
  }
];
