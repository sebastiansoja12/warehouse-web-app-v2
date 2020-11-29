import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Depot} from './depot';
import {Observable} from 'rxjs';

@Injectable()
export class DepotService {

    depotUrl: string;

  constructor(private http: HttpClient) {
    this.depotUrl = 'http://localhost:8080/api/depots';
  }

  public findAll(): Observable<Depot[]> {
    return this.http.get<Depot[]>(this.depotUrl);
  }
  public save(depot: Depot) {
    return this.http.post<Depot>(this.depotUrl, depot);
  }
}
