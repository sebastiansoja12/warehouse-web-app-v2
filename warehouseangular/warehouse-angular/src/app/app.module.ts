import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SignupComponent } from './auth/signup/signup.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './auth/login/login.component';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import {TokenInterceptor} from './token-interceptor';
import {HomeComponent} from './home/home.component';
import { DepotListComponent } from './depot-list/depot-list.component';
import { DepotFormComponent } from './depot-form/depot-form.component';
import {DepotService} from './auth/service/depot-service.service';
import { DepotViewComponent } from './depot-view/depot-view.component';
import {environment} from '../environments/environment';
import { ParcelAddComponent } from './parcel-add/parcel-add.component';
import {ParcelService} from './auth/service/parcel.service';
import {Parcel} from './auth/model/parcel';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    DepotListComponent,
    DepotFormComponent,
    DepotViewComponent,
    ParcelAddComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    FormsModule,


  ],
  providers: [

    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    Parcel,
    ParcelService,
    DepotService,
    DepotFormComponent,
    DepotViewComponent,
    DepotListComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
