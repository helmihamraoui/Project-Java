import { ApiService } from './../api.service';
import { Component } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-appointemnt',
  imports: [NabrapatientsComponent,CommonModule,RouterModule],
  templateUrl: './appointemnt.component.html',
  styleUrl: './appointemnt.component.css'
})
export class AppointemntComponent {
  listOfAppointments: any; // List of appointments
  userId:number=0;
  patientId:number=0;
  patient:any;
  constructor(private ApiService:ApiService) {}
  ngOnInit(): void {
    this.ApiService.getPatientByUserId().subscribe((data: any) => {
      this.patient = data;
      this.patientId = this.patient.id;
      console.log(this.patientId);
      this.ApiService.getAppointmentsForPatients(this.patientId).subscribe(data => {
        this.listOfAppointments = data;
        console.log(this.listOfAppointments);
      });
    });
  }
  isPastAppointment(appointmentTime: string | Date): boolean {
    const appointmentDate = new Date(appointmentTime); // Convert to Date object
    const currentDate = new Date(); // Get the current date and time
    return appointmentDate < currentDate; // Return true if it's in the past
  }
  //invoce the api service FOR DELET ONE APPOINTMENT
  deleteApp(id: number): void {
    this.ApiService.deleteAppointment(id).subscribe(() => {
      this.listOfAppointments = this.listOfAppointments.filter((appointment: any) => appointment.id !== id);
    });
  }
}
