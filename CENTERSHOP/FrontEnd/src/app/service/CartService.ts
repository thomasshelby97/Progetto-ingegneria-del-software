import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import {ICartProdotto} from '../models/ICartProdotto'

@Injectable()
export class CartService {

  public carrello :ICartProdotto[] = [

] ;
  
  constructor() { }

  addProducts(prodotto: ICartProdotto) {
    this.carrello.push(prodotto)
  }

}