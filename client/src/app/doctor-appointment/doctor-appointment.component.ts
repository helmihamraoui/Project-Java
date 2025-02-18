import { Component } from '@angular/core';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChatComponent } from '../chat/chat.component';

@Component({
  selector: 'app-doctor-appointment',
  imports: [NavbardocterComponent,CommonModule, FormsModule,ChatComponent],
  templateUrl: './doctor-appointment.component.html',
  styleUrls: ['./doctor-appointment.component.css']
})
export class DoctorAppointmentComponent {
  appointments:any[]=[]
  filtredAppointments:any[]=[]
  listOfAppointments:any[]=[]
  selectedDoctorId: number =0;
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
isPastAppointment(appointmentTime: string | Date): boolean {
    const appointmentDate = new Date(appointmentTime); // Convert to Date object
    const currentDate = new Date(); // Get the current date and time
    return appointmentDate < currentDate; // Return true if it's in the past
  }
  //invoce the api service FOR DELET ONE APPOINTMENT
  deleteApp(id: number): void {
    this.apiService.deleteAppointment(id).subscribe(() => {
      this.filtredAppointments = this.filtredAppointments.filter((appointment: any) => appointment.id !== id);
    });
  }
  chat(doctorId: number) {
    console.log('Chat with doctor:', doctorId);
    this.selectedDoctorId = doctorId; // Set the ID when chat button is clicked
  console.log('Chat with doctor:', this.selectedDoctorId);
  }
}
