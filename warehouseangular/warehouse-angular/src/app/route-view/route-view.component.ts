import {Component, Injectable, OnInit, Output, ViewChild, AfterViewInit, Input} from '@angular/core';
import { Router } from '@angular/router';
import { RouteService} from '../auth/service/route-service.service';
import {ActivatedRoute} from '@angular/router';
import {RouteFormComponent} from '../route-find/route-form.component';
import {FormGroup} from '@angular/forms';
import {LocalStorageService} from 'ngx-webstorage';
import {Subscription} from 'rxjs';
import {Route} from '../auth/model/route';


@Component({
  selector: 'app-depot-view',
  templateUrl: './route-view.component.html',
  styleUrls: ['./route-view.component.css']
})

export class RouteViewComponent implements  OnInit {
  @ViewChild(RouteFormComponent) child;


  routes: Route[];
 id: string;
  routeSub: Subscription;

  constructor(private routeService: RouteService, private routeForm: RouteFormComponent,
              private localStorage: LocalStorageService, private router: Router, private activatedRoute: ActivatedRoute) {

  }
  ngOnInit(): any {
   this.routeSub = this.activatedRoute.params.subscribe(params => {
      this.id = params.id;
    });
   this.routeService.getAllRoutesByParcelId(this.id).subscribe(dane => {
    this.routes = dane;
   });
  }



}
