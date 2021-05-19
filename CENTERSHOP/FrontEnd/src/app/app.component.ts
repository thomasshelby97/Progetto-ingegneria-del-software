import { AfterViewInit, Component } from "@angular/core";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'CenterShop';
  config;
  

  constructor(
    private http: HttpClient,
  ){this.http.get("assets/config.json").subscribe(data => {
    this.config= data;
    });}
  
  async ngAfterViewInit() {
  
    
    setInterval(async () => {
       
      const username = this.config.username;
      const password = this.config.password;
      const headers = { 'Authorization': 'Bearer '+ this.config.adminToken};
      var response = await this.http.post<any>(`http://localhost:8080/authenticate`,  { username, password }, { headers }).toPromise();
      this.config.adminToken = response.jwt;
      this.timePassed();
      
    }, 1200000);
  }

 async timePassed(){
    const headers = { 'Authorization': 'Bearer '+ this.config.adminToken};
    await this.http.put<any>(`http://localhost:8080/admin/time`,{},{ headers }).toPromise();
  }
 

}