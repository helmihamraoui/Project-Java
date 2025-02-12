import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  registerData: any = {firstName:'',lastName:'', email: '', password: '' ,confirm:'',date:'',number:'',address:'',image:'',role:'ROLE_PATIENT'}; 
  currentStep = 1;
  selectedImage: File | null = null;
  previewImage: string | ArrayBuffer | null = null; 
  meesageerr:any={};

  constructor(
    private apiService: ApiService,
    private router: Router
  ) {}
  onInit(){
    this.meesageerr={};
  }
  showStep(step: number) {
    if (step < 1 || step > 3) return;
    if (this.currentStep === 1 && !this.validateStep1()) return;
    if (this.currentStep === 2 && !this.validateStep2()) return;
    this.currentStep = step;
  } 

  validateStep1() {
    // Add validation logic for step 1
    // Example: Check if firstName and lastName are not empty
    if (!this.registerData.firstName || !this.registerData.lastName) {
      this.meesageerr.step1 = 'First name and last name are required.';
      return false;
    }
    return true;
  }

  validateStep2() {
    // Add validation logic for step 2
    // Example: Check if email and password are not empty
    if (!this.registerData.email || !this.registerData.password) {
      this.meesageerr.step2 = 'Email and password are required.';
      return false;
    }
    return true;
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
        this.router.navigate(['/urgent']);
      },
      error: (error) => {
        console.error('Registration error:', error);
      }
    });
  }
}