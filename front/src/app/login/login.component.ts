import { Component } from '@angular/core';
import { ConfigService } from '../config.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  user:any;
  password:any;
  constructor(private config: ConfigService, private router: Router, private route: ActivatedRoute) {
  }

  login(){
    this.user = (<HTMLInputElement>document.getElementById('username')).value
    this.password = (<HTMLInputElement>document.getElementById('password')).value
    var res = this.config.getUser(this.user, this.password)
    res.subscribe((data) => {
      if(data.status == "Successful"){
        this.router.navigate(['/home'])
        localStorage.setItem('user', this.user)
      }
      else alert("Invalid Username or Password")
    })
  }
}

