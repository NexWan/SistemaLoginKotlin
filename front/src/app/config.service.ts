import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  url:any = 'api/';
  constructor(private http: HttpClient) { }

  getUser(user:string, password:string){
    return this.http.get<{status: string}>(this.url + 'login' + `?user=${user}&password=${password}`)
  }
}
