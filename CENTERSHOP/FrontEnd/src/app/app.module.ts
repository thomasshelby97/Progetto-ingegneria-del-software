import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { ShipperComponent } from './shipper/shipper.component';
import { MerchantComponent } from './merchant/merchant.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { CartService } from './service/CartService';
import { CheckMerchService } from './service/CheckMerchService';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    SideBarComponent,
    ShipperComponent,
    MerchantComponent,
    ProductComponent,
    CartComponent,
    CheckoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [CartService,CheckMerchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
