import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbardocter',
  imports: [CommonModule,RouterModule],
  templateUrl: './navbardocter.component.html',
  styleUrl: './navbardocter.component.css'
})
export class NavbardocterComponent {
  constructor(private router:Router){}
    async logout(){
      await localStorage.clear()
      this.router.navigate(["/login"])
    }
}
