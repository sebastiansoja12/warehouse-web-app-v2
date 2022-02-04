import {Component, HostListener, OnInit} from '@angular/core';
import {Depot} from '../auth/model/depot';
import {DepotService} from '../auth/service/depot.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginRequestPayload} from '../auth/login/login-request.payload';
import {AuthService} from '../auth/service/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-depot-all',
  templateUrl: './depot-all.component.html',
  styleUrls: ['./depot-all.component.css']
})
export class DepotAllComponent implements OnInit {

  depots: Depot[];
  isCollapsed = true;
  focus;
  focus1;
  focus2;
  isError: boolean;

  constructor(
    private depotService: DepotService
  ) { }
  ngOnInit(): void {
    this.depotService.findAll().subscribe(data => {
      this.depots = data;
    });
  }
  @HostListener('document:mousemove', ['$event'])
  // tslint:disable-next-line:typedef
  onMouseMove(e)
  {
    const squares1 = document.getElementById('square1');
    const squares2 = document.getElementById('square2');
    const squares3 = document.getElementById('square3');
    const squares4 = document.getElementById('square4');
    const squares5 = document.getElementById('square5');
    const squares6 = document.getElementById('square6');
    const squares7 = document.getElementById('square7');
    const squares8 = document.getElementById('square8');

    const posX = e.clientX - window.innerWidth / 2;
    const posY = e.clientY - window.innerWidth / 6;

    squares1.style.transform =
      'perspective(500px) rotateY(' +
      posX * 0.05 +
      'deg) rotateX(' +
      posY * -0.05 +
      'deg)';
    squares2.style.transform =
      'perspective(500px) rotateY(' +
      posX * 0.05 +
      'deg) rotateX(' +
      posY * -0.05 +
      'deg)';
    squares3.style.transform =
      'perspective(500px) rotateY(' +
      posX * 0.05 +
      'deg) rotateX(' +
      posY * -0.05 +
      'deg)';
    squares4.style.transform =
      'perspective(500px) rotateY(' +
      posX * 0.05 +
      'deg) rotateX(' +
      posY * -0.05 +
      'deg)';
    squares5.style.transform =
      'perspective(500px) rotateY(' +
      posX * 0.05 +
      'deg) rotateX(' +
      posY * -0.05 +
      'deg)';
    squares6.style.transform =
      'perspective(500px) rotateY(' +
      posX * 0.05 +
      'deg) rotateX(' +
      posY * -0.05 +
      'deg)';
    squares7.style.transform =
      'perspective(500px) rotateY(' +
      posX * 0.02 +
      'deg) rotateX(' +
      posY * -0.02 +
      'deg)';
    squares8.style.transform =
      'perspective(500px) rotateY(' +
      posX * 0.02 +
      'deg) rotateX(' +
      posY * -0.02 +
      'deg)';
  }
}
