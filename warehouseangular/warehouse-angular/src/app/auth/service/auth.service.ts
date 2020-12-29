import { Injectable, Output, EventEmitter } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { SignupRequestPayload } from '../signup/singup-request.payload';
import { Observable, throwError } from 'rxjs';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginRequestPayload } from '../login/login-request.payload';
import { LoginResponse } from '../login/login-response.payload';
import { map, tap } from 'rxjs/operators';
import {User} from '../model/user';
import {Route} from '../model/route';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();
  @Output() role: EventEmitter<string> = new EventEmitter();
  @Output() admin: EventEmitter<boolean> = new EventEmitter();
  @Output() user: EventEmitter<User[]> = new EventEmitter();
  @Output() depotcode: EventEmitter<string> = new EventEmitter();



  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName(),
    role: this.getRole()
  };

  constructor(private httpClient: HttpClient,
              private localStorage: LocalStorageService) {
  }

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    return this.httpClient.post('http://localhost:8080/api/users/signup', signupRequestPayload, { responseType: 'text' });
  }
  login(loginRequestPayload: LoginRequestPayload): Observable<boolean> {
    return this.httpClient.post<LoginResponse>('http://localhost:8080/api/users/login',
      loginRequestPayload).pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('username', data.username);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('expiresAt', data.expiresAt);
      this.localStorage.store('role', data.role);
      this.username.emit(data.username);
      this.role.emit(data.role);
      return true;
    }));
  }
  getCurrentUser(): any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic '
    });

    return this.httpClient.get<User>('http://localhost:8080/api/users/currentuser');
  }

    getDepotCode(): string {
    return this.localStorage.retrieve('depotCode');
  }

  getJwtToken(): string {
    return this.localStorage.retrieve('authenticationToken');
  }
  getRole(): string
  {
    return this.localStorage.retrieve('role');
  }

  refreshToken(): any {
    return this.httpClient.post<LoginResponse>('http://localhost:8080/api/users/refresh/token',
      this.refreshTokenPayload)
      .pipe(tap(response => {
        this.localStorage.store('role', response.role);
        this.localStorage.store('authenticationToken', response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      }));
  }

  logout(): any {
    this.httpClient.post('http://localhost:8080/api/users/logout', this.refreshTokenPayload,
      { responseType: 'text' })
      .subscribe(data => {
        console.log(data);
      }, error => {
        throwError(error);
      });
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('expiresAt');
    this.localStorage.clear('role');
  }

  getUserName(): string {
    return this.localStorage.retrieve('username');
  }

  getRefreshToken(): string {
    return this.localStorage.retrieve('refreshToken');
  }

  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
  isAdmin(): boolean {
   return this.getRole() === 'admin';
  }
  isNull(): boolean {
    return this.getRole() != null;
  }
}
