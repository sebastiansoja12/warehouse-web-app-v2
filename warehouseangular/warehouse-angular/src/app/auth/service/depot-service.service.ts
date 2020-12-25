import { Injectable, AfterViewInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Depot} from '../model/depot';
import {Observable} from 'rxjs';
import { map, tap } from 'rxjs/operators';
import {LoginResponse} from '../login/login-response.payload';
import {LocalStorageService} from 'ngx-webstorage';
import {DepotRequestPayload} from '../../depot-form/depot-request.payload';
import {User} from '../model/user';
import {DepotInformation} from '../model/DepotInformation';


@Injectable()
export class DepotService {

  depotUrl: string;
  depotinfUrl: string;


  constructor(private http: HttpClient, private localStorage: LocalStorageService) {
    this.depotUrl = 'http://localhost:8080/api/depots/all';
    this.depotinfUrl = 'http://localhost:8080/api/depots/information/all';
  }


  public findAll(): Observable<Depot[]> {
    return this.http.get<Depot[]>(this.depotUrl);
  }
  public findAllDepots(): Observable<DepotInformation[]> {
    return this.http.get<DepotInformation[]>(this.depotinfUrl);
  }
  public getCity(): string{
    return this.localStorage.retrieve('city');
  }
  public save(depot: Depot) {
    return this.http.post<Depot>(this.depotUrl, depot);
  }
  getAllDepotsByParcelCode(parcelCode: string): Observable<Array<Depot>> {
      return this.http.get<Array<Depot>>('http://localhost:8080/api/depots/all/parcelCode/' + parcelCode);
  }
}
