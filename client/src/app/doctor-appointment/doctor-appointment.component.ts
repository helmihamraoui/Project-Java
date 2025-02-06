import { Component } from '@angular/core';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-doctor-appointment',
  imports: [NavbardocterComponent,CommonModule, FormsModule],
  templateUrl: './doctor-appointment.component.html',
  styleUrl: './doctor-appointment.component.css'
})
export class DoctorAppointmentComponent {
  appointments:any[]=[]
  filtredAppointments:any[]=[]
    search:String=""
  doctor:any
  constructor(private apiService:ApiService){}
  ngOnInit(){
    this.apiService.getAppointments().subscribe(data=>{
      this.appointments=data
      this.filtredAppointments=data
    })
    this.apiService.getDoctorByUserId().subscribe(data=>{
      this.doctor=data
    })
    
  }
  filter(){
    const query=this.search.toLowerCase().trim()
      console.log(this.search)
      this.filtredAppointments=this.appointments.filter(app =>
        app.patient.user.firstname.toLowerCase().includes(query)||
        app.patient.user.lastname.toLowerCase().includes(query)
      )
  }
}
