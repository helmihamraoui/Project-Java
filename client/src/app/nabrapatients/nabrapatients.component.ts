import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-nabrapatients',
  imports: [CommonModule,RouterModule],
  templateUrl: './nabrapatients.component.html',
  styleUrl: './nabrapatients.component.css'
})
export class NabrapatientsComponent {
constructor(private router:Router){}
  async logout(){
    await localStorage.clear()
    this.router.navigate(["/login"])
  }
}
