import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../config.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit{
  constructor(private config: ConfigService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(){
    this.config.veryifyLogin().subscribe((data) => {
      if(data.status != "Successful") this.router.navigate(['/']).then(r => console.log(r))
    })
  }

  logout(){
    this.config.logout().subscribe((data) => {
      if(data.status == "Successful") this.router.navigate(['/']).then(r => console.log(r))
    })
  }
}
