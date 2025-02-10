import { Component, OnInit } from '@angular/core';
import { Message } from '../message.model';
import { MessageService } from '../message.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  senderId: number = 0; // ID of the sender
  receiverId: number = 0; // ID of the receiver
  messages: Message[] = []; // Array to store messages
  newMessage: string = ''; // New message input
  constructor(private messageService: MessageService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.senderId = Number(localStorage.getItem('userId')); // Get sender ID from local storage

    this.route.paramMap.subscribe(params => {
      this.receiverId = Number(params.get('receiverId')); // Get receiver ID from route parameters

      this.connectToWebSocket(); // Connect to WebSocket
      this.loadChat(); // Load chat messages
    });

    this.messageService.messages$.subscribe((message) => {
      console.log('Received message in component:', message);
      if (message && (message.sender.id === this.receiverId || message.receiver.id === this.senderId)) {
        this.messages.push(message); // Push received message to messages array
      }
    });
  }

  connectToWebSocket(): void {
    this.messageService.connect(this.senderId); // Connect sender to WebSocket
    this.messageService.connect(this.receiverId); // Connect receiver to WebSocket
  }

  loadChat(): void {
    this.messageService.getChat(this.senderId, this.receiverId).subscribe((data) => {
      this.messages = data; // Load chat messages from service
      console.log('Chat loaded:', this.messages);
    });
  }

  sendMessage(): void {
    if (this.newMessage.trim()) {
      console.log('Sending message:', this.newMessage);
  
      const message: Message = {
        sender: { id: this.senderId },
        receiver: { id: this.receiverId },
        message: this.newMessage
      };
      this.messageService.sendMessageWebSocket(this.senderId, this.receiverId, this.newMessage); // Send message via WebSocket
  
      this.messages.push(message); // Push new message to messages array
      this.newMessage = ''; // Clear new message input
    }
  }
}
