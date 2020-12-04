import { Component, OnInit } from '@angular/core';
import {Depot} from '../auth/model/depot';
import {DepotService} from '../auth/service/depot-service.service';
import {Observable} from 'rxjs';
import {DepotFormComponent} from '../depot-form/depot-form.component';



@Component({
  selector: 'app-depot-list',
  templateUrl: './depot-list.component.html',
  styleUrls: ['./depot-list.component.css']
})
export class DepotListComponent implements OnInit {

  depots: Depot[];
  parcelCode: string;


  constructor(private depotService: DepotService) {
  }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.depotService.findAll().subscribe(data => {
      this.depots = data;
    });
  }


}
