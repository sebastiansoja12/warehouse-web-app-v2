import { Component, OnInit } from '@angular/core';
import {Depot} from '../depot';
import {ActivatedRoute, Router} from '@angular/router';
import {DepotService} from '../depot-service.service';

@Component({
  selector: 'app-depot-form',
  templateUrl: './depot-form.component.html',
  styleUrls: ['./depot-form.component.css']
})
export class DepotFormComponent  {

  depot: Depot;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private depotService: DepotService) {
    this.depot = new Depot();
  }
  onSubmit() {
    this.depotService.save(this.depot).subscribe(result => this.goToDepotList());
  }
  goToDepotList() {
    this.router.navigate(['/depots']);
  }
}
