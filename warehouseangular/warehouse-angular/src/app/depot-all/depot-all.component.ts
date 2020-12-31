import { Component, OnInit } from '@angular/core';
import {Depot} from '../auth/model/depot';
import {DepotService} from '../auth/service/depot.service';

@Component({
  selector: 'app-depot-all',
  templateUrl: './depot-all.component.html',
  styleUrls: ['./depot-all.component.css']
})
export class DepotAllComponent implements OnInit {

  depots: Depot[];
  constructor(
    private depotService: DepotService
  ) { }

  ngOnInit(): void {
    this.depotService.findAll().subscribe(data => {
      this.depots = data;
    });
  }

}
