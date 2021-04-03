import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/service/auth.service';
import {User} from '../auth/model/user';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  users: Array<User>;
  users2: User;
  email: string;
  depotCode: string;
  firstName: string;
  city: string;
  username: string;
  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.authService.getCurrentLoggedInUser().subscribe(data => {
   this.users = data;
    });
   /* this.email = this.authService.getEmail();
    this.firstName = this.authService.getFirstName();
    this.depotCode = this.authService.getDepotCode();
    this.username = this.authService.getUserName();
    this.city = this.authService.getCity();
*/


  }

}
