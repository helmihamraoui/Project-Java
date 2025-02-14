import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { ChatComponent } from '../chat/chat.component';
import { MessageService } from '../message.service';
import { Message } from '../message.model';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-docter-dashbord',
  imports: [NavbardocterComponent,CommonModule,ChatComponent,RouterModule],
  templateUrl: './docter-dashbord.component.html',
  styleUrl: './docter-dashbord.component.css'
})
export class DocterDashbordComponent  implements OnInit {
    patients:any[]=[]
    doctors:any[]=[]
    oneDoctor:any
    selectedDoctorId: number =0; // Store selected doctor's ID
 // Defining the component class and implementing OnInit interface
  messages: Message[] = []; // Initializing an array to hold messages

  constructor(private messageService: MessageService,private apiService:ApiService) { } // Injecting MessageService into the component


  showNotification(message: Message): void {
    //this.messages.push(message); // Adding the new message to the messages array

    // Checking if notifications are supported and permission is granted
    if ('Notification' in window && Notification.permission === 'granted') {
      new Notification('New Message', {
        body: message.message, // Setting the body of the notification to the message content
        icon: '/path-to-your-icon.png' // Setting the icon of the notification
      });
    }
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
  chat(doctorId: number) {
    console.log('Chat with doctor:', doctorId);
    this.selectedDoctorId = doctorId; // Set the ID when chat button is clicked
  console.log('Chat with doctor:', this.selectedDoctorId);
  }


    ngOnInit(){
      this.apiService.getAlldoctors().subscribe(data=>{
        this.doctors=data
      })
      this.apiService.getallPatient().subscribe(data=>{
        this.patients=data
      })
      this.apiService.getDoctorByUserId().subscribe(data=>{
        this.oneDoctor=data
        const userId = Number(localStorage.getItem('userId')); // Getting user ID from local storage
    this.messageService.connect(userId); // Connecting to the message service with the user ID

    // Subscribing to incoming messages
    this.messageService.messages$.subscribe((message: Message) => {
      console.log('New message received:', message);
      this.fetchAllMessages(); // Fetching all messages when a new message is received
      this.showNotification(message); // Calling showNotification method when a new message is received
    });

    // Requesting notification permission from the user
    if ('Notification' in window) {
      Notification.requestPermission().then((permission) => {
        if (permission === 'granted') {
          console.log('Notification permission granted.');
        }
      });
    }

    this.fetchAllMessages(); // Fetch messages on initialization
      })
    }
}
