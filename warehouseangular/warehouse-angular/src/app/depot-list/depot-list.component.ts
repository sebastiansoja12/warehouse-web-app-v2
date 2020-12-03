import { Component, OnInit } from '@angular/core';
import {Depot} from '../auth/model/depot';
import {DepotService} from '../auth/service/depot-service.service';
import {Observable} from 'rxjs';



@Component({
  selector: 'app-depot-list',
  templateUrl: './depot-list.component.html',
  styleUrls: ['./depot-list.component.css']
})
export class DepotListComponent implements OnInit {

  depots: Depot[];

  constructor(private depotService: DepotService) {
  }

  ngOnInit() {
    this.depotService.getAllDepots().subscribe(data => {
      this.depots = data;
    });
  }


}
