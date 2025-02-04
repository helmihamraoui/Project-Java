import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly baseUrl = 'http://localhost:8080/api'; // Update with your backend URL

  constructor(private http: HttpClient) {}
  registerUser(data:any): Observable<any> {
    return this.http.post(`${this.baseUrl}/v1/auth/register`, data).pipe(
      catchError(this.handleError)  // Handle errors gracefully
    );}
  // User login
  login(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/v1/auth/authenticate`, data).pipe(
      catchError(this.handleError)  // Handle errors gracefully
    );
  }
logout(): void {
    localStorage.removeItem('token'); // Remove token on logout
}
AddPatient(data:any,id:number): Observable<any> {
    return this.http.post(`${this.baseUrl}/v1/auth/any/patient/new/${id}`, data).pipe(
      catchError(this.handleError)  // Handle errors gracefully
    );
  }
  // Check if user is authenticated
  isAuthenticated(): boolean {
    return !!localStorage.getItem('token'); // Check if token exists
  }

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  private handleError(error: any): Observable<never> {
    console.error('API Error:', error);
    return throwError(() => new Error(error.message || 'Server Error'));
  }
}
