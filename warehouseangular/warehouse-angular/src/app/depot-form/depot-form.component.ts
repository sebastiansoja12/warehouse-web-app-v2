import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Depot} from '../auth/model/depot';
import {ActivatedRoute, Router} from '@angular/router';
import {DepotService} from '../auth/service/depot-service.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../auth/service/auth.service';
import {ToastrService} from 'ngx-toastr';
import {DepotRequestPayload} from './depot-request.payload';
import {LocalStorageService} from 'ngx-webstorage';


@Component({
  selector: 'app-depot-form',
  templateUrl: './depot-form.component.html',
  styleUrls: ['./depot-form.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class DepotFormComponent implements OnInit {

  depot: Array<Depot>;
  depotFindForm: FormGroup;
 dss: string;
parcelCode: string;
  @Output() parseParcelCode: EventEmitter<string> = new EventEmitter();

  isError: boolean;


  constructor(private depotService: DepotService, private router: Router,
              private toastr: ToastrService, private localStorage: LocalStorageService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.depotFindForm = new FormGroup({
      parcelCode: new FormControl('', Validators.required)
    });

  }

// tslint:disable-next-line:typedef
findParcelCode(): string {
 return this.localStorage.retrieve('parcelCode');
}

  findDepot() {
   this.parcelCode = this.depotFindForm.get('parcelCode').value;
   this.localStorage.store('parcelCode', this.parcelCode);
   this.parseParcelCode.emit(this.parcelCode);
   this.depotService.getAllDepotsByParcelCode(this.parcelCode).subscribe(data => {
     this.depot = data;
     this.router.navigate(
       ['/depot'],
       {
         relativeTo: this.activatedRoute,
         queryParams: { parcelCode: this.parcelCode },
         queryParamsHandling: 'merge',
         preserveFragment: true
       });
   });
  }


}
