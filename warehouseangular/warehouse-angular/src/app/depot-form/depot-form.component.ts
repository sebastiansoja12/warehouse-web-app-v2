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

  depot: Depot;
  depotFindForm: FormGroup;
 parcelCode: string;
  @Output() parseParcelCode: EventEmitter<String> = new EventEmitter();
  isError: boolean;


  constructor(private depotService: DepotService, private router: Router,
              private toastr: ToastrService, private localStorage: LocalStorageService) {
  }

  ngOnInit() {
    this.depotFindForm = new FormGroup({
      parcelCode: new FormControl('', Validators.required)
    });
  }

// tslint:disable-next-line:typedef
findParcelCode(){
 return this.parcelCode = this.depotFindForm.get('parcelCode').value;
}

  findDepot() {
   this.parcelCode = this.depotFindForm.get('parcelCode').value;
   this.parseParcelCode.emit(this.parcelCode);
   localStorage.setItem('parcelCode', this.parcelCode);

   this.depotService.getAllDepotsByParcelCode(this.parcelCode).subscribe(data => {
      this.router.navigate(['/depot'],
        { queryParams: { parcelCode: this.parcelCode } });
    }, error => {
      console.log(error);
      this.toastr.error('Nie znaleziono paczki o kodzie: ', this.parcelCode);
    });
  }


}
