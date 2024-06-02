import { Routes } from '@angular/router';
import { HomeComponent } from './presentation/home/home.component';
import { LoginScreen } from './presentation/auth/login/login.component';
import { RegisterScreen } from './presentation/auth/register/register.component';
import { DashboardComponent } from './presentation/dashboard/dashboard.component';
import { authGuard } from './core/guards/auth.guard';
import { AccountSettingsComponent } from './presentation/account-settings/account-settings.component';
import { MagiclinkComponent } from './presentation/auth/magiclink/magiclink.component';
import { InvoicesComponent } from './presentation/account-settings/invoices/invoices.component';
import { EditAccountComponent } from './presentation/account-settings/edit-account/edit-account.component';

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
    path: 'auth',
    component: MagiclinkComponent,
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
  },
  {
    path: 'account-settings',
    component: AccountSettingsComponent,
    canActivate: [ authGuard ],
    children: [
      {
        path: 'edit',
        component: EditAccountComponent
      },
      {
        path:'invoices',
        component: InvoicesComponent
      },
    ]
  },
];
