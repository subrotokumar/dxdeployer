import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
@Component({
  selector: 'Home-Navbar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit {
  constructor(private route: Router) {}

  ngOnInit(): void {}

  navToLogin() {
    this.route.navigate(['login']);
  }
}
