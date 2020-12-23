import { Component, OnInit } from '@angular/core';
import {Depot} from '../auth/model/depot';
import {DepotService} from '../auth/service/depot-service.service';
import {Observable} from 'rxjs';
import {DepotFormComponent} from '../depot-form/depot-form.component';
import {User} from '../auth/model/user';
import {AuthService} from '../auth/service/auth.service';



@Component({
  selector: 'app-depot-list',
  templateUrl: './depot-list.component.html',
  styleUrls: ['./depot-list.component.css']
})
export class DepotListComponent implements OnInit {

  depots: Depot[];
  username: string;

  constructor(private depotService: DepotService, private authService: AuthService) {
  }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.depotService.findAll().subscribe(data => {
      this.depots = data;
    });
  }


}
