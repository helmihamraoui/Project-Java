import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-login',
  imports:[ RouterModule, FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginData = { email: '', password: '' }; 
  errorMessage = '';  // Error message to display on failure
  successMessage = '';  // Success message on successful login
  constructor( private apiService: ApiService, private router: Router) {  }

  login(): void {
    this.apiService.login(this.loginData).subscribe({
      next: (response) => {
        console.log(response);  // Log the response to see the token and user ID
        this.successMessage = 'Login successful!';  // Show success message
        this.errorMessage = '';  // Clear any previous error messages
        // Store the JWT token in local storage
        localStorage.setItem('token', response.token);
        // Optionally, store the user ID or other necessary info
        localStorage.setItem('userId', response.id);
        // Optionally, store the user role
        localStorage.setItem('role', response.role);
        // Redirect based on user role
        //if response.role is ROLE_PATIENT redirect to patient dashboard
        //if response.role is ROLE_DOCTOR redirect to doctor dashboard
        //if response.role is ROLE_ADMIN redirect to admin dashboard
        if (response.role === 'ROLE_PATIENT') {
          this.router.navigate(["/patient/dashbord"]);
        } else if (response.role === 'ROLE_DOCTOR') {
          this.router.navigate(["/"]);
        } else if (response.role === 'ROLE_ADMIN') {
          this.router.navigate(["/"]);
        }
      },
      error: (error) => {
        this.errorMessage="Mail or password invalid";
        this.successMessage = '';  // Clear success message if present
        console.error('Login error:', error);  // Log the error for debugging
      }
    });
  }
}