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
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      sender_telephone: new FormControl('', Validators.required),
      destination_telephone: new FormControl('', Validators.required),
      destination_address: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      custom: new FormControl('', Validators.required)
    });
  }
  makeTrue(): boolean {
    return this.parcel.custom = true;
  }

  createParcel(): any {
    this.parcel.firstName = this.createParcelForm.get('firstName').value;
    this.parcel.lastName = this.createParcelForm.get('lastName').value;
    this.parcel.sender_telephone = this.createParcelForm.get('sender_telephone').value;
    this.parcel.destination_telephone = this.createParcelForm.get('destination_telephone').value;
    this.parcel.destination_address = this.createParcelForm.get('destination_address').value;
    this.parcel.email = this.createParcelForm.get('email').value;
    this.parcel.custom = this.createParcelForm.get('custom').value;
    this.parcelService.save(this.parcel).subscribe(() => {
        this.router.navigate(['/'],
          { queryParams: { sent: 'true' } });
      }, error => {
        console.log(error);
      });
  }


}
