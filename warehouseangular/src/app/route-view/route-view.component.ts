import {Component, Injectable, OnInit, Output, ViewChild, AfterViewInit, Input} from '@angular/core';
import { Router } from '@angular/router';
import { RouteService} from '../auth/service/route-service.service';
import {ActivatedRoute} from '@angular/router';
import {RouteFormComponent} from '../route-find/route-form.component';
import {FormGroup} from '@angular/forms';
import {LocalStorageService} from 'ngx-webstorage';
import {Subscription, throwError} from 'rxjs';
import {Route} from '../auth/model/route';


@Component({
  selector: 'app-route-view',
  templateUrl: './route-view.component.html',
  styleUrls: ['./route-view.component.css']
})

export class RouteViewComponent implements  OnInit {
  @ViewChild(RouteFormComponent) child;


  routes: Route[];
 id: string;
  routeSub: Subscription;
   isError: boolean;
  private message: string;

  constructor(private routeService: RouteService, private routeForm: RouteFormComponent,
              private localStorage: LocalStorageService, private router: Router, private activatedRoute: ActivatedRoute) {

  }
  ngOnInit(): any {
   this.routeSub = this.activatedRoute.params.subscribe(params => {
      this.id = params.id;
    });
   this.routeService.getAllRoutesByParcelId(this.id).subscribe(data => {
     this.isError = false;
     this.routes = data;
    }, error => {
      this.isError = true;
      throwError(error);
      this.message = 'Paczka o id: ' +  this.id + ' nie została znaleziona.\n' +
        'Sprawdź numer swojej przesyłki i spróbuj ponownie';
    });

  }



}
