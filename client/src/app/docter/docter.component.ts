import { Component } from '@angular/core';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component'; 
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-docter',
  imports: [NavbardocterComponent,RouterModule, FormsModule, CommonModule],
  
  templateUrl: './docter.component.html',
  styleUrl: './docter.component.css'
})
export class DocterComponent {
  listOfDays: string[] = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
  

}
