import { Component, OnInit } from '@angular/core';
import {Route} from '../auth/model/route';
import { RouteService} from '../auth/service/route-service.service';
import {Observable} from 'rxjs';
import {RouteFormComponent} from '../route-find/route-form.component';
import {User} from '../auth/model/user';
import {AuthService} from '../auth/service/auth.service';
import {LocalStorageService} from 'ngx-webstorage';



@Component({
  selector: 'app-depot-list',
  templateUrl: './route-list.component.html',
  styleUrls: ['./route-list.component.css']
})
export class RouteListComponent implements OnInit {

  routes: Route[];
  depotcode: string;
  users: User[];

  constructor(private routeService: RouteService, private authService: AuthService,
              private localStorage: LocalStorageService) {
  }

  // tslint:disable-next-line:typedef

  ngOnInit(): any {
    this.routeService.findAll().subscribe(data => {
      this.routes = data;
    });

  }
}
