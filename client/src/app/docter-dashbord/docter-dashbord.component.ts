import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component';

@Component({
  selector: 'app-docter-dashbord',
  imports: [NavbardocterComponent],
  templateUrl: './docter-dashbord.component.html',
  styleUrl: './docter-dashbord.component.css'
})
export class DocterDashbordComponent {

}
