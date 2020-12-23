import { Component, OnInit } from '@angular/core';
import {ParcelService} from '../auth/service/parcel.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Parcel} from '../auth/model/parcel';
import {throwError} from 'rxjs';
import {Router} from '@angular/router';
import {AuthService} from '../auth/service/auth.service';

@Component({
  selector: 'app-parcel-add',
  templateUrl: './parcel-add.component.html',
  styleUrls: ['./parcel-add.component.css']
})
export class ParcelAddComponent implements OnInit {

  createParcelForm: FormGroup;

  constructor(
    private parcelService: ParcelService, private parcel: Parcel,
    private router: Router, private authService: AuthService
  ) {}

  ngOnInit() {
    this.createParcelForm = new FormGroup({
      parcelCode: new FormControl('', Validators.required),
      isCustom: new FormControl('')
    });
  }

  createParcel() {
    this.parcel.kodPaczki = this.createParcelForm.get('parcelCode').value;
    this.authService.isLoggedIn();
    this.parcelService.save(this.parcel).subscribe(() => {
        this.router.navigate(['/'],
          { queryParams: { parcelCode: this.parcel.kodPaczki } });
      }, error => {
        console.log(error);
      });
  }

}
