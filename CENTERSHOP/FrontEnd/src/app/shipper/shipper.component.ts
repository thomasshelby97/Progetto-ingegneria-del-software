import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {IShipment} from '../models/IShipment'
import { Subscription } from 'rxjs';




@Component({
  selector: 'app-shipper',
  templateUrl: './shipper.component.html',
  styleUrls: ['./shipper.component.css']
})

export class ShipperComponent implements OnInit {



  constructor(private http: HttpClient,) {}

  shipment: IShipment[] = [
    { codiceRitiro:"",orario:"",nominativoCliente:"",negozi:[""], viaPuntoRitiro:""
     },
  ];

 
  ngOnInit(): void {

  }

  
  shipmentEventHandler($event:IShipment){
    this.shipment[0]=$event

  }   

  async endConsegna(ship:IShipment){
    
    var d = new Date();
    var hours = d.getHours();
    var minutes = d.getMinutes();
    if(minutes<10)
    var orario = hours+":0"+minutes;
    else 
    var orario = hours+":"+minutes;
    const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
    await this.http.post<any>(`http://localhost:8080/corriere/endConsegna`,{codiceritiro :ship.codiceRitiro, orario : orario },{ headers }).toPromise();
    this.shipment.splice(this.shipment.indexOf(ship),1);
    window.location.reload()
    
  }


}
setInterval(() => {
  window.location.reload()
}, 120000);