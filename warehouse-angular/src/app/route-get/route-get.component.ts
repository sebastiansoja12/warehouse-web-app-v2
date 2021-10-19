import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RouteService} from '../auth/service/route-service.service';
import {ParcelService} from '../auth/service/parcel.service';
import {LocalStorageService} from 'ngx-webstorage';
import {Depot} from '../auth/model/depot';
import {Parcel} from '../auth/model/parcel';
import {Route} from '../auth/model/route';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-route-get',
  templateUrl: './route-get.component.html',
  styleUrls: ['./route-get.component.css']
})
export class RouteGetComponent implements OnInit {

  getRouteForm: FormGroup;
  depot: Depot;
  isError: boolean;
  routes: Route[];
  routesList: Route[];
  constructor(private routeService: RouteService, private parcelService: ParcelService,
              private localStorage: LocalStorageService,
              private router: Router,
              private parcel: Parcel,
              private route: Route,
              private toastr: ToastrService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getRouteForm = new FormGroup({
      id: new FormControl('', Validators.required),
      custom: new FormControl('', Validators.required)
    });
    this.routeService.findAll().subscribe(data => {
      this.routes = data;
    });

    this.routeService.findAll().subscribe(data => {
      this.routesList = data;
    });
  }

  getRoute(): any {
    this.parcel.id  = this.getRouteForm.get('id').value;
    this.parcel.custom  =  this.getRouteForm.get('custom').value;
    this.route.parcel = this.parcel;
    this.routeService.save(this.route).subscribe(data => {
      this.isError = false;
    }, error => {
      this.isError = true;
      window.location.assign('/');
      this.toastr.success('Błędny kod lub paczka została już zarejestrowana');
      throwError(error);

    });
    window.location.reload();
  }

  printLabel(): any {
    this.parcel.id  = this.getRouteForm.get('id').value;
    window.location.href = 'http://localhost:8080/api/parcels/' + this.parcel.id + '/label';

  }

  toCSV(): any {
    this.parcel.id  = this.getRouteForm.get('id').value;
    window.location.href = 'http://localhost:8080/api/parcels/' + this.parcel.id + '/csv';
  }
}
