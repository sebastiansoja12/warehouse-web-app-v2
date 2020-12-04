import {Component, Injectable, OnInit, Output, ViewChild, AfterViewInit, Input} from '@angular/core';
import {Depot} from '../auth/model/depot';
import {DepotService} from '../auth/service/depot-service.service';
import {ActivatedRoute} from '@angular/router';
import {DepotFormComponent} from '../depot-form/depot-form.component';
import {FormGroup} from '@angular/forms';
import {LocalStorageService} from 'ngx-webstorage';


@Component({
  selector: 'app-depot-view',
  templateUrl: './depot-view.component.html',
  styleUrls: ['./depot-view.component.css']
})

export class DepotViewComponent implements  OnInit {
  @ViewChild(DepotFormComponent) child;


  depots: Depot[];
 parcelCode: string;

  constructor(private depotService: DepotService, private depotForm: DepotFormComponent, private localStorage: LocalStorageService) {

  }
  ngOnInit() {
   this.parcelCode =  localStorage.getItem('parcelCode');
   this.depotService.getAllDepotsByParcelCode(this.parcelCode).subscribe(data => {
      this.depots = data;
    });
  }



}
