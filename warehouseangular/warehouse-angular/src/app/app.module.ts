import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SignupComponent } from './auth/signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './auth/login/login.component';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import {TokenInterceptor} from './token-interceptor';
import {HomeComponent} from './home/home.component';
import { DepotComponent } from './depot/depot.component';
import { DepotListComponent } from './depot-list/depot-list.component';
import { DepotFormComponent } from './depot-form/depot-form.component';
import {DepotService} from './depot-service.service';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    DepotComponent,
    DepotListComponent,
    DepotFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    DepotService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
