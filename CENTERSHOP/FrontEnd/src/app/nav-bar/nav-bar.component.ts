import { AfterViewInit, Component, OnInit ,Output,EventEmitter} from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Subscription } from 'rxjs';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit, AfterViewInit {
  constructor(private http: HttpClient) {}
 
  logged : String;


  
  message:string;
  subscription: Subscription;
  
  @Output() codeEvent1 = new EventEmitter<String>();
  
  async ngOnInit(): Promise<void> {
    if(sessionStorage.getItem('ruolo')=="CORRIERE") {
      const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
      this.logged = await this.http.get<any>(`http://localhost:8080/corriere/data`,{ headers }).toPromise();
      }
     
  }

  async ngAfterViewInit() {
    if(sessionStorage.getItem('ruolo')=="CORRIERE") {
    const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
    var corriere = await this.http.get<any>(`http://localhost:8080/corriere/data`,{ headers }).toPromise();
    this.logged = corriere.username;
    }
    if(sessionStorage.getItem('ruolo')=="COMMERCIANTE") {
      const headers = { 'Authorization': 'Bearer '+ sessionStorage.getItem('token')};
      var commerciante = await this.http.get<any>(`http://localhost:8080/commerciante/data`,{ headers }).toPromise();
      this.logged = commerciante.username;
      }
  }

  isLogged() {
    if (sessionStorage.getItem('ruolo') != null) return true;
    else return false;
  }
  isLogin() {
    if (
      window.location.href.substr(window.location.href.lastIndexOf('/') + 1) ==
      'login' || window.location.href.substr(window.location.href.lastIndexOf('/') + 1) ==
      'checkout'
    )
      return false;
    else return true;
  }
  logout() {
    sessionStorage.removeItem('ruolo');
    sessionStorage.removeItem('token');
  }

  onSearchChange(value){
  
  }

  goCenter(){
    
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('ruolo');
    window.location.reload()
  }

}
