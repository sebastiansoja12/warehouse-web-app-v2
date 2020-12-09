import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';

import { AuthGuard } from './auth/auth.guard';
import {HomeComponent} from './home/home.component';
import {DepotListComponent} from './depot-list/depot-list.component';
import {DepotViewComponent} from './depot-view/depot-view.component';
import {DepotFormComponent} from './depot-form/depot-form.component';
import {ParcelAddComponent} from './parcel-add/parcel-add.component';

const routes: Routes = [
  { path: 'sign-up', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'depots/all', component: DepotListComponent },
  { path: 'depot', component: DepotViewComponent },
  { path: 'depot/find', component: DepotFormComponent },
  { path: 'parcel/add', component: ParcelAddComponent }





];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
