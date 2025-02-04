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
        this.router.navigate(['/urgent']);
      },
      error: (error) => {
        console.error('Registration error:', error);
      }
    });
  }
}