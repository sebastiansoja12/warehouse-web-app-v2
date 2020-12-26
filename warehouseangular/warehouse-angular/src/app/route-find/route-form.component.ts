import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Route} from '../auth/model/route';
import {ActivatedRoute, Router} from '@angular/router';
import { RouteService} from '../auth/service/route-service.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../auth/service/auth.service';
import {ToastrService} from 'ngx-toastr';
import {RouteRequestPayload} from './route-request.payload';
import {LocalStorageService} from 'ngx-webstorage';
import {throwError} from 'rxjs';


@Component({
  selector: 'app-depot-form',
  templateUrl: './route-form.component.html',
  styleUrls: ['./route-form.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class RouteFormComponent implements OnInit {

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

// tslint:disable-next-line:typedef
findParcelCode(): string {
 return this.localStorage.retrieve('id');
}

  findParcel(): any {
   this.id = this.parcelFindForm.get('id').value;
   this.localStorage.store('id', this.id);
   this.parseParcelId.emit(this.id);
   this.routeService.getAllRoutesByParcelId(this.id).subscribe(data => {
     this.routes = data;
     this.toastr.success('Paczka znaleziona');
     this.isError = false;
     this.router.navigate(
         ['/route'],
         {
           relativeTo: this.activatedRoute,
           queryParams: { parcelCode: this.id },
           queryParamsHandling: 'merge',
           preserveFragment: true
         });
   }, error => {
     this.isError = true;
     throwError(error);
     this.toastr.error('Paczka nie została znaleziona');
     this.message = 'Paczka o id: ' +  this.id + ' nie została znaleziona w bazie.\n' +
                       'Sprawdź numer swojej przesyłki i spróbuj ponownie';
   });
  }
}
