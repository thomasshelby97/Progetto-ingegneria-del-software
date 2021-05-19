import { AfterViewInit, Component, EventEmitter, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {IShipment} from '../models/IShipment'

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css'],
})
export class SideBarComponent implements OnInit {

  ruolo: string;
  nome:String;
  id: any;
  prezzo: any;
  disponibilita: any;
  descrizione: any;
  immagine: any;
  promozione: any;
  categoria: String;


  menuItems: any[] = [
  ];

  shipment: IShipment[] = [
  ]

  merchants: any[]=[
    
  ]

  constructor(
    private http: HttpClient,
  ) { }
  @Output() codeEvent = new EventEmitter<IShipment>();
  @Output() codeEvent1 = new EventEmitter<String>();
  
  @Output() codeEvent2 = new EventEmitter<String>();


  async ngOnInit() {
    const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
    if(this.isShipper()) 
      this.shipment = await this.http.get<any>(`http://localhost:8080/corriere/getConsegne`,{ headers }).toPromise();
    if(this.isHome()) {
      const userHeader = {'currentUser' : sessionStorage.getItem('currentUser')};
      this.menuItems = await this.http.get<any>(`http://localhost:8080/user/categorie`, ).toPromise();
    }
    
    
  }

  isHome() {
    if(sessionStorage.getItem('ruolo')==null)
    return true;
    return false;
  }

  isShipper(){
    if(sessionStorage.getItem('ruolo')=="CORRIERE") 
      return true;
    return false;
  }
  isMerchant(){
    if(sessionStorage.getItem('ruolo')=="COMMERCIANTE")
    return true;
    return false;
  }

  sendCode(ship:IShipment){
    this.codeEvent.emit(ship)
  }

  sendCategory(item){
   this.codeEvent1.emit(item);
  }

  showCatalogo(){
    this.codeEvent1.emit("tutti");
  }
  showCatalogoScontato(){
    this.codeEvent1.emit("scontati");
  }

  mostraProdotti(){
    this.codeEvent2.emit("Tutti");
  }

  mostraScontati(){
    this.codeEvent2.emit("Scontati");
  }
 


  //----------------FUNZIONE AGGIUNGI PRODOTTO
  addNome(value){
    this.nome=value;
  }
  addCategoria(value){
    this.categoria=value;
  }
  addPrezzo(value){
    this.prezzo=value;
  }
  addDisponibilita(value){
    this.disponibilita=value;
  }
  addPromozione(value){
    this.promozione=value;
  }
  addDescrizione(value){
    this.descrizione=value;
  }
  addImmagine(value){
    this.immagine=value;
  }
  addId(value) {
    this.id = value;
  }

  
  async aggiungiProdotto(){
    const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
    await this.http.post<any>(`http://localhost:8080/commerciante/addNewProdotto`, {prezzo: this.prezzo, descrizione: this.nome, 
        categoria: this.categoria, disponibilita: this.disponibilita, persconto: this.promozione, img:this.immagine},{headers}).toPromise();
        window.location.reload();
  }

  //-----------------FUNZIONE MODIFICA DIPONIBILITà
async modificaDisp(){
  const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
  await this.http.put<any>(`http://localhost:8080/commerciante/cambiaDisponibilita`, {id: this.id,disponibilita: this.disponibilita},{headers}).toPromise();
  window.location.reload();
}

//-----------------FUNZIONE Cancella prodotto
async cancProd(){
  const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
  await this.http.put<any>(`http://localhost:8080/commerciante/removeProdotto`, {id: this.id},{headers}).toPromise();
  window.location.reload();
}
//-----------------FUNZIONE MODIFICA PROMOZIONE
async modificaPromozione(){
  const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
  await this.http.post<any>(`http://localhost:8080/commerciante/addPromozione`, {id: this.id, sconto: this.promozione},{headers}).toPromise();
  window.location.reload();
}






}
