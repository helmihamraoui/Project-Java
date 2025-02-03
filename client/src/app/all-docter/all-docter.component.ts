import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { AddDocterComponent } from '../add-docter/add-docter.component';

@Component({
  selector: 'app-all-docter',
  imports: [NavbarComponent,AddDocterComponent],
  templateUrl: './all-docter.component.html',
  styleUrl: './all-docter.component.css'
})
export class AllDocterComponent {

}
