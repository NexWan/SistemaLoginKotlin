import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  url:any = 'api/';
  constructor(private http: HttpClient) { }

  getUser(user:string, password:string){
    return this.http.post<{status:string}>(this.url + 'login', {username: user, password: password})
  }

  veryifyLogin(){
    return this.http.get<{status: string}>(this.url + 'verifySession')
  }

  logout(){
    return this.http.get<{status: string}>(this.url + 'logout')
  }
}
