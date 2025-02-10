import { Component, OnInit } from '@angular/core'; // Importing necessary Angular core components
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component'; // Importing NabrapatientsComponent
import { CommonModule } from '@angular/common'; // Importing CommonModule for common Angular directives
import { RouterModule } from '@angular/router'; // Importing RouterModule for routing functionalities
import { ApiService } from '../api.service'; // Importing ApiService for API interactions
import { MessageService } from '../message.service'; // Importing MessageService for message handling
import { Message } from '../message.model'; // Importing Message model
import { ChatComponent } from '../chat/chat.component';

@Component({
  selector: 'app-patintsdashbord', // Defining the selector for the component
  imports: [NabrapatientsComponent, CommonModule, RouterModule ,ChatComponent], // Importing other components and modules
  templateUrl: './patintsdashbord.component.html', // Defining the template URL
  styleUrls: ['./patintsdashbord.component.css'] // Defining the styles URL
})
export class PatintsdashbordComponent implements OnInit { // Defining the component class and implementing OnInit interface
  messages: Message[] = []; // Initializing an array to hold messages
  selectedDoctorId: number =0; // Store selected doctor's ID

  constructor(private messageService: MessageService) { } // Injecting MessageService into the component

  ngOnInit(): void {
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
}
