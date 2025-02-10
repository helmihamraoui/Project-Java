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
  registerData: any = {firstName: '', lastName: '', email: '', password: '', confirm: '', date: '', number: '', address: '',role:"ROLE_PATIENT"};
  currentStep = 1;

  constructor(private apiService: ApiService, private router: Router) {}

  showStep(step: number) {
    if (step < 1 || step > 2) return;
    this.currentStep = step;
  }

  submitRegistration() {
    console.log(this.registerData);
    this.apiService.registerUser(this.registerData).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('userId', response.id);
        localStorage.setItem('role', response.role);
        this.router.navigate(['/urgent']);
      },
      error: (error) => {
        console.error('Registration error:', error);
      }
    });
  }
}
