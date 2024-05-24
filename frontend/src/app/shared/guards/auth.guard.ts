import { CanActivateFn, Router } from "@angular/router";
import { AccountsService } from "../../services/accounts.service";
import { inject } from "@angular/core";

export const authGuard: CanActivateFn = (route, state) => {
    const authService = inject(AccountsService);
    const router = inject(Router)
    const accessToken = localStorage.getItem("access_token")
    console.log("AccessToken "+ accessToken)
    if(!accessToken){
        router.navigateByUrl("/login")
        return false;
    }
    return true;
  };
