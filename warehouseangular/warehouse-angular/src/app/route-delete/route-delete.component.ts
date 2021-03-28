import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RouteService} from '../auth/service/route-service.service';
import {ParcelService} from '../auth/service/parcel.service';
import {LocalStorageService} from 'ngx-webstorage';
import {Router} from '@angular/router';
import {Parcel} from '../auth/model/parcel';
import {Route} from '../auth/model/route';

@Component({
  selector: 'app-route-delete',
  templateUrl: './route-delete.component.html',
  styleUrls: ['./route-delete.component.css']
})
export class RouteDeleteComponent implements OnInit {
  getRouteForm: FormGroup;
  id: string;

  constructor(private routeService: RouteService, private parcelService: ParcelService,
              private localStorage: LocalStorageService,
              private router: Router,
              private parcel: Parcel,
              private route: Route) { }

  ngOnInit(): void {
    this.getRouteForm = new FormGroup({
      id: new FormControl('', Validators.required),
    });
  }

  deleteRoute(): any {
    this.id  = this.getRouteForm.get('id').value;
    this.route.parcel = this.parcel;
    this.routeService.deleteRouteByParcelId(this.id).subscribe(() => {
      this.router.navigate(['/'],
        { queryParams: { deleted: this.parcel.id } });
    }, error => {
      console.log(error);
      this.router.navigate(['/'],
        { queryParams: { deleted: this.parcel.id } });
    });
  }
}
