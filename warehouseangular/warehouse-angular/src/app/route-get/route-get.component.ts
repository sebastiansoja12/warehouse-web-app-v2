import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RouteService} from '../auth/service/route-service.service';
import {ParcelService} from '../auth/service/parcel.service';
import {LocalStorageService} from 'ngx-webstorage';
import {Depot} from '../auth/model/depot';
import {Parcel} from '../auth/model/parcel';
import {Route} from '../auth/model/route';
import {Router} from '@angular/router';

@Component({
  selector: 'app-route-get',
  templateUrl: './route-get.component.html',
  styleUrls: ['./route-get.component.css']
})
export class RouteGetComponent implements OnInit {

  getRouteForm: FormGroup;
  depot: Depot;
  constructor(private routeService: RouteService, private parcelService: ParcelService,
              private localStorage: LocalStorageService,
              private router: Router,
              private parcel: Parcel,
              private route: Route) { }

  ngOnInit(): void {
    this.getRouteForm = new FormGroup({
      id: new FormControl('', Validators.required),
      custom: new FormControl('', Validators.required)
    });
  }

  getRoute(): any {
    this.parcel.id  = this.getRouteForm.get('id').value;
    this.parcel.custom  =  this.getRouteForm.get('custom').value;
    this.route.parcel = this.parcel;
    this.routeService.save(this.route).subscribe(() => {
      this.router.navigate(['/'],
        { queryParams: { parcelId: this.parcel.id } });
    }, error => {
      console.log(error);
    });
  }

  printLabel(): any {
    this.parcel.id  = this.getRouteForm.get('id').value;
    window.location.href = 'http://localhost:8080/api/parcels/toPDF/' + this.parcel.id;

  }

  toCSV(): any {
    this.parcel.id  = this.getRouteForm.get('id').value;
    window.location.href = 'http://localhost:8080/api/parcels/csv/' + this.parcel.id;
  }
}
