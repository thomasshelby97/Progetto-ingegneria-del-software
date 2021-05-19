import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { CheckoutComponent } from './checkout/checkout.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AccountModule } from './login/login.module';
import { MerchantComponent } from './merchant/merchant.component';
import { ShipperComponent } from './shipper/shipper.component';
import { AuthGuard } from "./login/_helpers";

const routes: Routes = [
  { 
    path: '', 
    component: HomeComponent },
  {
    path: 'login',
    component: LoginComponent,
  },
  { 
    path: 'shipper', 
    component: ShipperComponent,
    canActivate : [AuthGuard],
},
  { 
    path: 'merchant', 
    component: MerchantComponent,
    canActivate : [AuthGuard],
},
  { 
    path: 'checkout', 
    component: CheckoutComponent 
},
];

@NgModule({
  imports: [AccountModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
