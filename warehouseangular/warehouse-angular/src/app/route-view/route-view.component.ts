import {Component, Injectable, OnInit, Output, ViewChild, AfterViewInit, Input} from '@angular/core';
import {Route} from '../auth/model/route';
import { RouteService} from '../auth/service/route-service.service';
import {ActivatedRoute} from '@angular/router';
import {RouteFormComponent} from '../route-find/route-form.component';
import {FormGroup} from '@angular/forms';
import {LocalStorageService} from 'ngx-webstorage';


@Component({
  selector: 'app-depot-view',
  templateUrl: './route-view.component.html',
  styleUrls: ['./route-view.component.css']
})

export class RouteViewComponent implements  OnInit {
  @ViewChild(RouteFormComponent) child;


  routes: Route[];
 id: string;

  constructor(private routeService: RouteService, private routeForm: RouteFormComponent,
              private localStorage: LocalStorageService) {

  }
  ngOnInit(): any {
   this.routeForm.parseParcelId.subscribe((data: string) => this.id = data);
   this.id = this.routeForm.findParcelCode();
   this.routeService.getAllRoutesByParcelId(this.id).subscribe(dane => {
    this.routes = dane;
   });
  }



}
