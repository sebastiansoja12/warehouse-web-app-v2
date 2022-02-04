import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
import {APP_BASE_HREF, DatePipe} from '@angular/common';
import { AdministratorComponent } from './administrator/administrator.component';
import {RouterModule} from '@angular/router';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ProgressbarModule } from 'ngx-bootstrap/progressbar';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { AlertModule } from 'ngx-bootstrap/alert';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { ModalModule } from 'ngx-bootstrap/modal';
import {PagesModule} from './pages/pages.module';
import {HeaderComponent} from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
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
    AdministratorComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    FormsModule,
    RouterModule,
    BsDropdownModule.forRoot(),
    ProgressbarModule.forRoot(),
    TooltipModule.forRoot(),
    CollapseModule.forRoot(),
    TabsModule.forRoot(),
    PaginationModule.forRoot(),
    AlertModule.forRoot(),
    BsDatepickerModule.forRoot(),
    CarouselModule.forRoot(),
    ModalModule.forRoot(),
    ReactiveFormsModule,
    PagesModule

  ],
  providers: [{provide: APP_BASE_HREF, useValue : '/' },
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
    AdministratorComponent
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class AppModule { }
