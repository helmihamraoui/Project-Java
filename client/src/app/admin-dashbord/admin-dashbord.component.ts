import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-dashbord',
  imports: [NavbarComponent,CommonModule],
  templateUrl: './admin-dashbord.component.html',
  styleUrl: './admin-dashbord.component.css'
})
export class AdminDashbordComponent {
  patients:any[]=[]
  doctors:any[]=[]
  appointments:any[]=[]

  constructor(private apiService:ApiService){}


  ngOnInit(){
    this.apiService.getallPatient().subscribe(data =>{
      this.patients=data
    })
    this.apiService.getAlldoctors().subscribe(data=>{
      this.doctors=data
    })
    this.apiService.getAppointments().subscribe(data =>{
      this.appointments=data
    })
  }
}
