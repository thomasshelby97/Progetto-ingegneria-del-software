import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { first } from "rxjs/operators";
import {AlertService } from "./_services";

@Component({ templateUrl: "login.component.html", styleUrls: ['./login.component.css'] })
export class LoginComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  ruolo:string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
    private http: HttpClient,
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });

    // get return url from route parameters or default to '/'
    /* this.returnUrl = this.route.snapshot.queryParams["returnUrl"] || "/"; */

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || ' ';
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.form.controls;
  }

  
  async onSubmit() {
    
    this.submitted = true;

    this.alertService.clear();
    
    this.loading = false;

    let username = this.f.username.value;
    let password = this.f.password.value;
    var token = await this.http.post<any>(`http://localhost:8080/authenticate`, { username, password }).toPromise();
    if(token.ruolo=="CORRIERE"){
      this.router.navigate(["shipper"]);
      sessionStorage.setItem('token',token.jwt);
      sessionStorage.setItem('ruolo',token.ruolo);
      sessionStorage.removeItem('currentUser');
    } else if (token.ruolo=="COMMERCIANTE") {
      this.router.navigate(["merchant"]);
      sessionStorage.setItem('token',token.jwt);
      sessionStorage.setItem('ruolo',token.ruolo);
      sessionStorage.removeItem('currentUser');
    }
    else {
      alert("OCCHIO")
    }

  }
}
