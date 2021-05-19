import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ICartProdotto} from '../models/ICartProdotto'
import { CheckMerchService } from '../service/CheckMerchService';

@Component({
  selector: 'app-merchant',
  templateUrl: './merchant.component.html',
  styleUrls: ['./merchant.component.css']
})
export class MerchantComponent implements OnInit {
  catalogo:any[] = [
   ]
  public carrello :String[] = [

  ] ;

  clienti : number;

  logged : any;
  constructor(private http: HttpClient,private merch : CheckMerchService) { }

 async ngOnInit(): Promise<void> {
   console.log(this.merch.carrello);
   console.log(this.carrello);
   
   this.carrello = this.merch.carrello;
   console.log(this.merch.carrello);
   
    const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
    this.catalogo = await this.http.get<any>(`http://localhost:8080/commerciante/prodotti`,{ headers }).toPromise();
    this.clienti = await this.http.get<any>(`http://localhost:8080/commerciante/clienti`,{ headers }).toPromise();
    this.logged = await this.http.get<any>(`http://localhost:8080/commerciante/data`,{ headers }).toPromise();
    var c = this.carrello.pop();
    if(c!=null) {
      alert("hai una richiesta: "+c);
    }
  }


 async merchantEventHandler($event:String){
   
    if($event=="Tutti") {
      const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
      this.catalogo = await this.http.get<any>(`http://localhost:8080/commerciante/prodotti`,{ headers }).toPromise();
    } else {
      if($event=="Scontati") {
        const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
        this.catalogo = await this.http.get<any>(`http://localhost:8080/commerciante/prodottiScontati`,{ headers }).toPromise();
      }
    }
    
  }   
  
}
setInterval(() => {
  window.location.reload()
}, 120000);
 
