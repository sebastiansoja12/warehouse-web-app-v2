import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Route} from '../model/route';
import {Depot} from '../model/depot';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService} from 'ngx-webstorage';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DepotService {

  depotUrl: string;

  constructor(
    private depot: Depot, private http: HttpClient, private localStorage: LocalStorageService,
    private authService: AuthService
  ) {
    this.depotUrl = 'http://localhost:8080/api/depots';
  }


  public findAll(): Observable<Depot[]> {
    return this.http.get<Depot[]>(this.depotUrl);
  }
}
