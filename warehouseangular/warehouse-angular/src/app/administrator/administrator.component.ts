import { Component, OnInit } from '@angular/core';
import {Route} from '../auth/model/route';
import {User} from '../auth/model/user';
import {RouteService} from '../auth/service/route-service.service';
import {AuthService} from '../auth/service/auth.service';
import {LocalStorageService} from 'ngx-webstorage';
import {UserService} from '../auth/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent implements OnInit {

  routes: Route[];
  depotcode: string;
  users: User[];
  username: string;
  routeSub: Subscription;

  constructor(private routeService: RouteService, private authService: AuthService,
              private localStorage: LocalStorageService, private userService: UserService, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  // tslint:disable-next-line:typedef
  firstName: string;

  ngOnInit(): any {
    this.routeSub = this.activatedRoute.params.subscribe(params => {
      this.username = params.id;
    });
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
    this.routeService.findByUsername(this.username).subscribe(data => {
      this.routes = data;
    });
  }
  deleteRouteByParcelId(id: string): any{
    this.routeService.deleteRouteByParcelId(id).subscribe(data => {
      this.routes = data;
    });
    window.location.reload();
  }

  setUsername(value): any{
    window.location.assign('/admin/routes/all/' + value);

  }


}
