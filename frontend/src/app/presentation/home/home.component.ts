import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { BodyComponent } from './body/body.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, BodyComponent],
  templateUrl: './home.component.html'
})
export class HomeComponent {
  constructor(private router: Router){}

  // ngOnInit(): void {
  //   // this.voidAuthCheck()
  // }

  // voidAuthCheck() {
  //   const token = localStorage.getItem("access_token")
  //   if(token){
  //     this.router.navigateByUrl("/dashboard")
  //   }
  // }
}
