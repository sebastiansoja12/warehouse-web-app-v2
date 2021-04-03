import { Component, OnInit } from '@angular/core';
import {Route} from '../auth/model/route';
import { RouteService} from '../auth/service/route-service.service';
import {Observable, Subscription} from 'rxjs';
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
  username: string;

  constructor(private routeService: RouteService, private authService: AuthService,
              private localStorage: LocalStorageService) {
  }

  // tslint:disable-next-line:typedef
  firstName: string;

  ngOnInit(): any {
    this.routeService.findAll().subscribe(data => {
      this.routes = data;
    });
    this.username = this.authService.getUserName();
    this.firstName = this.authService.getFirstName();
  }
  deleteRouteByParcelId(id: string): any{
    this.routeService.deleteRouteByParcelId(id).subscribe(data => {
      this.routes = data;
    });
    window.location.reload();
  }
}
