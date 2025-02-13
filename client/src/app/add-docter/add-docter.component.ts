import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-docter',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-docter.component.html',
  styleUrl: './add-docter.component.css'
})
export class AddDocterComponent {
  data: any={specialties:"",jobTitle:"",experience:0,licenseNumb:0}
  id:number=0
  constructor(
      private apiService: ApiService,
      private router: Router
    ) {}

  submitRegistration(){
    console.log(this.data); 
    
    //get the user id from local storage
    this.id = Number(localStorage.getItem('userId'));
        this.apiService.addDoctor(this.data, this.id).subscribe({
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
