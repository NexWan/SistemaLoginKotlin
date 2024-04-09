import { Component } from '@angular/core';
import { RouterOutlet, ActivatedRoute, Router } from '@angular/router';
import { ConfigService } from './config.service';
import { HeaderComponent } from './layout/header/header.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'tutorial';

}
