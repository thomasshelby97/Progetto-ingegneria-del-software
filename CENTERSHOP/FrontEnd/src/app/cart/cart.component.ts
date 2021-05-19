import { Component, Input, OnInit } from '@angular/core';
import {ICartProdotto} from '../models/ICartProdotto'
import { CartService } from '../service/CartService';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})

export class CartComponent implements OnInit {
  constructor(private cartService : CartService) { };

  carrelloFinale : ICartProdotto[] = [

  ];

  cart:ICartProdotto[]=[
    {id:"",descrizione:"",prezzo: 0 ,quantita: 0}
  ]

  @Input() carrello :ICartProdotto[] = [
  ] ;

  ngOnInit(): void {
    
    }


  homeEventHandler($event:ICartProdotto){
    this.cart[0]=$event
  } 
  
  checkout(){
    this.carrello.forEach(element => {
      this.cartService.addProducts(element);
    });
  } 


  getTotal(){
    var total =0;
    this.carrello.forEach(element => {
      total += element.prezzo*element.quantita
    });
    return total;
  }
}
