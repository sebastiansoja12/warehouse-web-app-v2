import { Component, OnInit } from '@angular/core';
import {Depot} from '../depot';
import {DepotService} from '../depot-service.service';



@Component({
  selector: 'app-depot-list',
  templateUrl: './depot-list.component.html',
  styleUrls: ['./depot-list.component.css']
})
export class DepotListComponent implements OnInit {

  depots: Depot[];

  constructor(private depotService: DepotService) {
  }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.depotService.findAll().subscribe(data => {
      this.depots = data;
    });
  }

}
