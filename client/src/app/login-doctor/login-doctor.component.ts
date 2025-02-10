import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router'; import { ApiService } from '../api.service';

import { Router} from '@angular/router';

@Component({
  selector: 'app-login-doctor',
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './login-doctor.component.html',
  styleUrl: './login-doctor.component.css' 
  
})
export class LoginDoctorComponent {
   registerData: any = {firstName:'',lastName:'', email: '', password: '' ,confirm:'',date:'',number:'',address:'',image:'',role:'ROLE_DOCTOR'}; 
    currentStep = 1;
    selectedImage: File | null = null;
    previewImage: string | ArrayBuffer | null = null;
  
    constructor(
      private apiService: ApiService,
      private router: Router
    ) {}
  
    showStep(step: number) {
      if (step < 1 || step > 3) return;
      this.currentStep = step;
    }
  
    submitRegistration() {
      console.log(this.registerData);
      this.apiService.registerUser(this.registerData).subscribe({
        next: (response) => {
          //set the token in local storage
          localStorage.setItem('token', response.token);
          //set the user id in local storage
          localStorage.setItem('userId', response.id);
          
          //set the Role in local storage
          localStorage.setItem('role', response.role);
          //redirect to the urgent page
          this.router.navigate(['/addocter']);
        },
        error: (error) => {
          console.error('Registration error:', error);
        }
      });
    }

}
