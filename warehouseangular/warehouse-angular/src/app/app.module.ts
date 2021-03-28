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
import { RouteListComponent } from './route-list/route-list.component';
import { RouteFormComponent } from './route-find/route-form.component';
import { RouteViewComponent } from './route-view/route-view.component';
import {environment} from '../environments/environment';
import { ParcelAddComponent } from './parcel-add/parcel-add.component';
import {ParcelService} from './auth/service/parcel.service';
import {Parcel} from './auth/model/parcel';
import {RouteService} from './auth/service/route-service.service';
import { RouteGetComponent } from './route-get/route-get.component';
import {Route} from './auth/model/route';
import { RouteDeleteComponent } from './route-delete/route-delete.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { DepotAllComponent } from './depot-all/depot-all.component';
import {Depot} from './auth/model/depot';
import { SideBarComponent } from './shared/side-bar/side-bar.component';
import {DatePipe} from '@angular/common';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    RouteListComponent,
    RouteFormComponent,
    RouteViewComponent,
    ParcelAddComponent,
    RouteGetComponent,
    RouteDeleteComponent,
    UserProfileComponent,
    DepotAllComponent,
    SideBarComponent,

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
DatePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    Depot,
    Parcel,
    Route,
    ParcelService,
    RouteService,
    RouteFormComponent,
    RouteViewComponent,
    RouteListComponent,
    RouteGetComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
