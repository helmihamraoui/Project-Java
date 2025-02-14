import { ApiService } from './../api.service';
import { Component, OnInit } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MessageService } from '../message.service';
import { Message } from '../message.model';
import { ChatComponent } from '../chat/chat.component';
import { tap, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-patintsdashbord',
  standalone: true,
  imports: [CommonModule, RouterModule, NabrapatientsComponent, ChatComponent],
  templateUrl: './patintsdashbord.component.html',
  styleUrls: ['./patintsdashbord.component.css']
})
export class PatintsdashbordComponent implements OnInit {
  messages: Message[] = [];
  selectedDoctorId: number = 0;
  myAppointments: any[] = [];
  patient: any = {};
  notif: string = '';
  constructor(private messageService: MessageService, private ApiService: ApiService) {}

  ngOnInit(): void {
    this.ApiService.getPatientByUserId().pipe(
      tap(patient => {
        this.patient = patient;
      }),
      switchMap(patient => this.ApiService.getAppointmentsForPatients(patient.id))
    ).subscribe(data => {
      this.myAppointments = data;
      console.log("Patient ID:", this.myAppointments);
      this.checkAppointmentsForTomorrow();
    });

    const userId = Number(localStorage.getItem('userId'));
    this.messageService.connect(userId);

    this.messageService.messages$.subscribe((message: Message) => {
      console.log('New message received:', message);
      this.fetchAllMessages();
      this.showNotification(message);
    });

    if ('Notification' in window) {
      Notification.requestPermission().then((permission) => {
        if (permission === 'granted') {
          console.log('Notification permission granted.');
        }
      });
    }
  }

  showNotification(message: Message): void {
    this.messages.push(message); // Adding the new message to the messages array

    // Checking if notifications are supported and permission is granted
    if ('Notification' in window && Notification.permission === 'granted') {
      new Notification('New Message', {
        body: message.message, // Setting the body of the notification to the message content
        icon: '/path-to-your-icon.png' // Setting the icon of the notification
      });
    }
  }

  chat(doctorId: number) {
    console.log('Chat with doctor:', doctorId);
    this.selectedDoctorId = doctorId;
  }

  getMyAppointments(id: number) {
    this.ApiService.getAppointmentsForPatients(id).subscribe(data => {
      console.log("Appointments:", data);
      this.myAppointments = data;
      this.checkAppointmentsForTomorrow();
    });
  }

  checkAppointmentsForTomorrow() {
    const now = new Date();
    const tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);

    this.myAppointments
      .filter(app => {
        const appointmentDate = new Date(app.time);
        const timeDiff = appointmentDate.getTime() - now.getTime();
        const hoursDiff = timeDiff / (1000 * 60 * 60); // Convert ms to hours

        return hoursDiff > 0 && hoursDiff <= 24; // Appointment is within the next 24 hours
      })
      .forEach(this.showAppointmentNotification.bind(this)); // Bind `this` context
  }

  fetchAllMessages(): void {
    console.log('Fetching all messages...');
    // Fetching all messages related to the user
    this.messageService.getLatestMessages(Number(localStorage.getItem('userId'))).subscribe((messages: Message[]) => {
      console.log('Messages fetched from the server:', messages);
      this.messages = messages; // Assigning the fetched messages to the messages array
      console.log('Updated messages array:', this.messages); // Logging the fetched messages
    }, (error) => {
      console.error('Error fetching messages:', error);
    });
  }
  showAppointmentNotification(appointment: any): void {
    const appointmentTime = new Date(appointment.time).toLocaleString();
    this.notif = `You have an appointment with ${appointment.doctor.user.firstName} ${appointment.doctor.user.lastName} at ${appointmentTime}.`;
    console.log('Notification message:', this.notif);

    if ('Notification' in window && Notification.permission === 'granted') {
      new Notification('Upcoming Appointment', {
        body: this.notif,
        icon: '/path-to-your-icon.png'
      });
    }
  }
}
