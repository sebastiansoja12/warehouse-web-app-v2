import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {throwError} from 'rxjs';
import {Route} from '../auth/model/route';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RouteService} from '../auth/service/route-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {LocalStorageService} from 'ngx-webstorage';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  routes: Array<Route>;
  parcelFindForm: FormGroup;
  dss: string;
  id: string;
  @Output() parseParcelId: EventEmitter<string> = new EventEmitter();

  isError: boolean;
  message: string;


  constructor(private routeService: RouteService, private router: Router,
              private toastr: ToastrService, private localStorage: LocalStorageService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): any {
    this.parcelFindForm = new FormGroup({
      id: new FormControl('', Validators.required)
    });

  }

  findParcel(): any {
    this.id = this.parcelFindForm.get('id').value;
    this.router.navigateByUrl('/route/parcelCode/' + this.id);
    this.routeService.getAllRoutesByParcelId(this.id).subscribe(data => {
      this.routes = data;
      this.isError = false;
    }, error => {
      this.isError = true;
      throwError(error);
      this.message = 'Paczka o id: ' +  this.id + ' nie została znaleziona.\n' +
        'Sprawdź numer swojej przesyłki i spróbuj ponownie';
    });
  }
}
