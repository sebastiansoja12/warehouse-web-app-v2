import { Injectable, AfterViewInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  depotUrl: string;
  depotinfUrl: string;
  registerParcel: string;


  constructor(private http: HttpClient, private localStorage: LocalStorageService) {
    this.depotUrl = 'http://localhost:8080/api/routes/all';
    this.depotinfUrl = 'http://localhost:8080/api/depots/all';
    this.registerParcel = 'http://localhost:8080/api/routes';

  }


  public findAll(): Observable<Route[]> {
    return this.http.get<Route[]>(this.depotUrl);
  }
  public findAllDepots(): Observable<Depot[]> {

    return this.http.get<Depot[]>(this.depotinfUrl);
  }
  public getCity(): string{
    return this.localStorage.retrieve('city');
  }
  public save(parcelRoute: Route): Observable<any>  {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic '});
    return this.http.post<Route>(this.registerParcel, parcelRoute, {headers});
  }
  getAllRoutesByParcelId(id: string): Observable<Array<Route>> {
      return this.http.get<Array<Route>>('http://localhost:8080/api/routes/all/parcelId/' + id);
  }
}
