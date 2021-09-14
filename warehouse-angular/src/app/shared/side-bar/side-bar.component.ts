import { Component, OnInit } from '@angular/core';
import {RouteService} from '../../auth/service/route-service.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RouteGetComponent} from '../../route-get/route-get.component';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  parcelFindForm: FormGroup;


  constructor(private routeService: RouteService, private router: Router, private routeForm: RouteGetComponent) { }

  ngOnInit(): any {
  }

  generateLabel(): any {
    this.routeForm.printLabel();
  }
  generateCSV(): any {
    this.routeForm.toCSV();
  }
}
