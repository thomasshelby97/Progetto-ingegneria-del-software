import { TmplAstRecursiveVisitor } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CartService } from '../service/CartService';
import {ICartProdotto} from '../models/ICartProdotto';
import { CheckMerchService } from '../service/CheckMerchService';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  config;
  realm : String;
  via : String;
  nome : String;
  cognome : String;
  email : String;
  numero : String;
  CVC : String;
  expiryDate : String;
  cardNumber : String;
  consegnaAddress : String;

  carrelloFinale : ICartProdotto[] = [

  ];

 

  constructor(
    private http: HttpClient,
    private cartService : CartService,
    private merch : CheckMerchService,
  ){this.http.get("assets/config.json").subscribe(data => {
    this.config= data;
    });}

  ngOnInit(): void {
    this.carrelloFinale = this.cartService.carrello;
  }

 async checkPayment(){
  console.log(this.config.adminToken);
    await this.findPuntoRitiro();
    
    
    const headers = { 'Authorization': 'Bearer '+ this.config.adminToken};
    var response = await this.http.post<any>(`http://localhost:8080/admin/payment`, {numeroCarta: this.cardNumber, codice:this.CVC, titolareCarta: this.nome,importo: 432}, { headers }).toPromise();
    var prodotti: String[] = [];
    this.carrelloFinale.forEach(element => {
      for (let index = 0; index < element.quantita; index++) {
        prodotti.push(element.id);
      }
      
    });
    
    if(response==true){
      var orario = await this.http.post<any>(`http://localhost:8080/admin/newConsegna`, {prodotti, puntoRitiro : this.consegnaAddress , nominativo: this.nome.concat(this.cognome.toString())}, { headers }).toPromise();
      prodotti.forEach(element => {
        this.merch.addProducts(element);
      });
      alert("Pagamento effettutato con successo, spedizione prevista alle ore : "+orario.ora+ ", nel seguente punto di ritiro: "+this.consegnaAddress+ " con codice ritiro: "+orario.codiceritiro);
    } else {
      alert("Pagamento rifiutato")
    }
  }


  getTotal(){
    var total =0;
    this.carrelloFinale.forEach(element => {
      total += element.prezzo*element.quantita
    });
    return total;
  }


 async findPuntoRitiro()  {
   console.log(this.config.adminToken);
   
    const headers = { 'Authorization': 'Bearer '+ this.config.adminToken};
    if(this.via==null)
      this.consegnaAddress = this.realm;
    else {
    var response = await this.http.post<any>(`http://localhost:8080/admin/checkRitiro`, {via : this.via}, { headers }).toPromise();
    
    if (response == true) 
      this.consegnaAddress = this.via;
    else {
      this.consegnaAddress = this.realm;
    }
  }
  }


  onRadioChange(value){
    this.realm = value;
  }

  onBoxChange(value){
    this.via = value;
  }
  onNomeChange(value){
    this.nome = value;
  }
  onCognomeChange(value){
    this.cognome = value;
  }
  onEmailChange(value){
    this.email = value;
  }
  onNumberChange(value){
    this.numero = value;
  }
  onCardNumberChange(value){
    this.cardNumber = value;
  }
  onExDataChange(value){
    this.expiryDate = value;
  }
  onCVCChange(value){
    this.CVC = value;
  }




}
