import { Injectable, AfterViewInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Depot} from '../model/depot';
import {Observable} from 'rxjs';
import { map, tap } from 'rxjs/operators';
import {LoginResponse} from '../login/login-response.payload';
import {LocalStorageService} from 'ngx-webstorage';
import {DepotRequestPayload} from '../../depot-form/depot-request.payload';


@Injectable()
export class DepotService {

  depotUrl: string;


  constructor(private http: HttpClient, private localStorage: LocalStorageService) {
    this.depotUrl = 'http://localhost:8080/api/depots/all';
  }


  public findAll(): Observable<Depot[]> {
    return this.http.get<Depot[]>(this.depotUrl);
  }

  public save(depot: Depot) {
    return this.http.post<Depot>(this.depotUrl, depot);
  }

  getAllDepots(): Observable<Array<Depot>> {
    return this.http.get<Array<Depot>>('http://localhost:8080/api/depots/all');
  }

  getAllDepotsByParcelCode(parcelCode: string): Observable<Array<Depot>> {
      return this.http.get<Array<Depot>>('http://localhost:8080/api/depots/all/parcelCode/' + parcelCode);
  }
}
