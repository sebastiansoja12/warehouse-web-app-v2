import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';

import { AuthGuard } from './auth/auth.guard';
import {HomeComponent} from './home/home.component';
import {RouteListComponent} from './route-list/route-list.component';
import {RouteViewComponent} from './route-view/route-view.component';
import {RouteFormComponent} from './route-find/route-form.component';
import {ParcelAddComponent} from './parcel-add/parcel-add.component';
import {RouteGetComponent} from './route-get/route-get.component';
import {RouteDeleteComponent} from './route-delete/route-delete.component';

const routes: Routes = [
  { path: 'sign-up', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'routes/all', component: RouteListComponent },
  { path: 'route', component: RouteViewComponent },
  { path: 'route/find', component: RouteFormComponent },
  { path: 'parcel/add', component: ParcelAddComponent },
  { path: 'route/add', component: RouteGetComponent },
  { path: 'route/delete', component: RouteDeleteComponent }







];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
