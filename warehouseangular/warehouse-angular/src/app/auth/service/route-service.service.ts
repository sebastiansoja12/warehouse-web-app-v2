import { Injectable, AfterViewInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Route} from '../model/route';
import {Observable} from 'rxjs';
import { map, tap } from 'rxjs/operators';
import {LoginResponse} from '../login/login-response.payload';
import {LocalStorageService} from 'ngx-webstorage';
import {RouteRequestPayload} from '../../route-find/route-request.payload';
import {User} from '../model/user';
import {Depot} from '../model/depot';
import {Parcel} from '../model/parcel';


@Injectable()
export class RouteService {

  routeUrl: string;
  depotinfUrl: string;
  registerParcel: string;
  userUrl: string;

  constructor(private http: HttpClient, private localStorage: LocalStorageService) {
    this.routeUrl = 'http://localhost:8080/api/routes';
    this.depotinfUrl = 'http://localhost:8080/api/depots';
    this.registerParcel = 'http://localhost:8080/api/routes';
    this.userUrl = 'http://localhost:8080/api/routes/user/';

  }

   findByUsername(username: string): Observable<Route[]> {
    return this.http.get<Route[]>('http://localhost:8080/api/routes/' + username + '/user');
  }

  public findAll(): Observable<Route[]> {
    return this.http.get<Route[]>(this.routeUrl);
  }
  public findAllDepots(): Observable<Depot[]> {

    return this.http.get<Depot[]>(this.depotinfUrl);
  }
  public getCity(): string{
    return this.localStorage.retrieve('city');
  }
  public save(parcelRoute: Route): Observable<boolean>  {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic '});
    return this.http.post<Route>(this.registerParcel, parcelRoute, {headers}).pipe(map(data => {
      return true;
    }));
  }
  getAllRoutesByParcelId(id: string): Observable<Array<Route>> {
      return this.http.get<Array<Route>>('http://localhost:8080/api/routes/' + id + '/parcelId');
  }
  deleteRouteByParcelId(id: string): any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic '});
    return this.http.post<Route>('http://localhost:8080/api/routes/' + id + '/parcelId', {headers});
  }
}
