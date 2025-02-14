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
    return this.http.post(`${this.baseUrl}/v1/any/patient/new/${id}`, data).pipe(
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

  // Appointments routes

  getAppointments():Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/appointments").pipe(
      catchError(this.handleError)
    )
  }
  getAppointmentsForPatients(id:number):Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/appointments/patient/"+id).pipe(
      catchError(this.handleError)
    )
  }
  getOneAppointment(id:number): Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/appointments/"+id).pipe(catchError(this.handleError));
  }
  getAppointmentByDoctorId(id:number):Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/appointments/doctor/"+id).pipe(catchError(this.handleError))
  }
  addAppointment(data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/v1/any/appointments/new",data).pipe(catchError(this.handleError))
  }
  // end Appointments

  // availability routes

  addAvailability(data:any ,id:number):Observable<any>{
    return this.http.post(this.baseUrl+"/v1/any/availability/new/"+id,data).pipe(catchError(this.handleError))
  }
  getAvailability(id:number):Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/availability/"+id).pipe(catchError(this.handleError))
  }
  // enď availability

  // doctor routes

  getAlldoctors():Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/doctors").pipe(catchError(this.handleError))
  }
  getOneDoctor(id:number):Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/doctors/"+id).pipe(catchError(this.handleError))
  }

  getDoctorByUserId():Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/doctor/"+localStorage.getItem("userId")).pipe(catchError(this.handleError))
  }

  addDoctor(data:any,id:number):Observable<any>{
  return this.http.post(`${this.baseUrl}/v1/any/doctors/new/${id}`, data).pipe(
    catchError(this.handleError)
  )}

  getallPatient():Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/patient/all").pipe(catchError(this.handleError))
  }



  getDiagnose(id:number):Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/diagnose/"+id).pipe(catchError(this.handleError))
  }

  addDiagnose(id:number,data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/v1/any/diagnose/new/"+id,data).pipe(catchError(this.handleError))
  }

  editDiagnose(id:number,data:any):Observable<any>{
    return this.http.put(this.baseUrl+"/v1/any/diagnose/add/"+id,data).pipe(catchError(this.handleError))
  }

  //get one patient by id
  getOnePatient(id:number):Observable<any>{
    return this.http.get(this.baseUrl+"/v1/any/patient/"+id).pipe(catchError(this.handleError))
  }
  getPatientByUserId():Observable<any>{
    return this.http.get(this.baseUrl+"/v1/patient/"+Number(localStorage.getItem("userId"))).pipe(catchError(this.handleError))
  }
  //delete appointment
  deleteAppointment(id:number):Observable<any>{
    return this.http.delete(this.baseUrl+"/v1/any/delete/appointments/"+id).pipe(catchError(this.handleError))
  }
  private handleError(error: any): Observable<never> {
    console.error('API Error:', error);
    return throwError(() => new Error(error.message || 'Server Error'));
  }

}
