import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Route} from '../model/route';
import {User} from '../model/user';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService} from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrl: string;
  adminUrl: string;



  constructor(private http: HttpClient, private localStorage: LocalStorageService) {
    this.userUrl = 'http://localhost:8080/api/users/all';
    this.adminUrl = 'http://localhost:8080/api/users/all/user/';


  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl);
  }

}
