import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/service/auth.service';
import { Router } from '@angular/router';
import 'node_modules/bootstrap/dist/css/bootstrap.min.css';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean;
  username: string;
  role: string;
  isLoggedInAsAdmin: boolean;
  isNull: boolean;
  isAdmin: boolean;

  constructor(private authService: AuthService, private router: Router) {
  }


  ngOnInit(): any {
    this.authService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
    this.authService.username.subscribe((data: string) => this.username = data);
    this.authService.role.subscribe((data: string) => this.role = data);
    this.authService.admin.subscribe( (data: boolean) => this.isAdmin = data);
    this.isNull = this.authService.isNull();
    this.isLoggedIn = this.authService.isLoggedIn();
    this.isAdmin = this.authService.isAdmin();
    this.username = this.authService.getUserName();


  }

  logout() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.router.navigateByUrl('');
  }


}
