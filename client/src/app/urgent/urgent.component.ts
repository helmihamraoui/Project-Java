import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-urgent',
  imports:[ RouterModule, FormsModule, CommonModule],
  templateUrl: './urgent.component.html',
  styleUrl: './urgent.component.css'
})
export class UrgentComponent {
  data: any = {emergContact:''};
  id: number = 0;
    constructor(
      private apiService: ApiService,
      private router: Router
    ) {}
  submit(): void {
    console.log(this.data); 
    
    //get the user id from local storage
    this.id = Number(localStorage.getItem('userId'));
        this.apiService.AddPatient(this.data, this.id).subscribe({
      next: (response) => {
        console.log(response);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Error:', error);
      }
    });
  }
}
