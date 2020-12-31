import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/service/auth.service';
import {User} from '../auth/model/user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  users: User[];
  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(data => {
      this.users = data;
    });
  }

}
