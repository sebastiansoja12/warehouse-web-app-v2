import { Injectable } from '@angular/core';
import {Route} from '../model/route';
import {Parcel} from '../model/parcel';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ParcelPayload} from '../../parcel-add/parcel-payload';
import {tap} from 'rxjs/operators';
import {LocalStorageService} from 'ngx-webstorage';
import {AuthService} from './auth.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParcelService {


  constructor(private http: HttpClient, private localStorage: LocalStorageService,
              private authService: AuthService) {
  }


  public save(parcel: Parcel): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', this.localStorage.retrieve('authenticationToken'));

    return this.http.post('http://localhost:8080/api/parcels', parcel, { responseType: 'text' });
  }
}
