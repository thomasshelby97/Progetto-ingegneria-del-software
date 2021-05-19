import { compileFactoryFunction } from '@angular/compiler';
import { AfterViewInit, Component, OnInit , Output, EventEmitter} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ICartProdotto} from '../models/ICartProdotto'
import {IProdotto} from '../models/IProdotto'
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {
  config;

  prodotti: IProdotto[] = [
  ]

  carrello: ICartProdotto[] = [

  ];


  constructor(private http: HttpClient,) { 
    this.http.get("assets/config.json").subscribe(data => {
      this.config = data;
      if(sessionStorage.getItem('currentUser')==null && sessionStorage.getItem('ruolo')==null){
        sessionStorage.setItem('currentUser',this.makeid(50));
      } 
      this.createUser(sessionStorage.getItem('currentUser'),this.config.adminToken)
    });
  }


  @Output() codeEvent = new EventEmitter<ICartProdotto>();


  sendToCart(prodotto, number){
    if(number>prodotto.disponibilita) 
      number = prodotto.disponibilita;
    if(number>0)
    this.carrello.push(new ICartProdotto(prodotto.id,prodotto.descrizione,prodotto.prezzo,number));
  }

  
   ngOnInit() {

  }

 

  async showCatalogo(){
    const headers = { 'currentUser' : sessionStorage.getItem('currentUser')};
    this.prodotti = await this.http.get<any>(`http://localhost:8080/user/prodotti`,{ headers }).toPromise();
    
  }


  async showCatalogoScontato(){
    const headers = { 'currentUser' : sessionStorage.getItem('currentUser')};
    this.prodotti = await this.http.get<any>(`http://localhost:8080/user/prodottiScontati`,{ headers }).toPromise();
  }

 async prodottiEventHandler($event: String){
   
    if($event=="tutti") {
     this.showCatalogo();
    } else 
    if($event=="scontati") {
    this.showCatalogoScontato();
    } else {
    const headers = { 'currentUser' : sessionStorage.getItem('currentUser')};
    this.prodotti = await this.http.post<any>(`http://localhost:8080/user/categoria`,{categoria: $event},{ headers }).toPromise();
    }
    
   
  }


  async ngAfterViewInit() {
    
    if(sessionStorage.getItem('currentUser')==null && sessionStorage.getItem('ruolo')==null){
      sessionStorage.setItem('currentUser',this.makeid(50));
    } 
    const headers = { 'currentUser' : sessionStorage.getItem('currentUser')};
    this.prodotti = await this.http.get<any>(`http://localhost:8080/user/prodotti`,{ headers }).toPromise();
    
    
  }
  
  makeid(length) {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
       result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }

    return result;
  }

  async createUser(id : String, adminToken){
    const headers = { 'Authorization': 'Bearer '+ adminToken};
    await this.http.post<any>(`http://localhost:8080/admin/cliente`,{id : id}, { headers });
  }

  
  

}
