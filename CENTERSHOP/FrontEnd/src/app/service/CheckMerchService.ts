import { Injectable } from '@angular/core';
import {ICartProdotto} from '../models/ICartProdotto'

@Injectable()
export class CheckMerchService {

  public carrello :String[] = [

] ;
  
  constructor() { }

  addProducts(prodotto: String) {
    this.carrello.push(prodotto)
    console.log("carrello: "+this.carrello);
    
  }

}