import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Message } from '../message.model';
import { MessageService } from '../message.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnChanges {
  senderId: number = 0; // ID of the sender
  receiverId: number = 0; // ID of the receiver
  messages: Message[] = []; // Array to store messages
  newMessage: string = ''; // New message input

  @Input() doctorId: number | null = null; // Receive doctorId from parent

  constructor(private messageService: MessageService) {}

  ngOnInit(): void {
    this.senderId = Number(localStorage.getItem('userId')); // Get sender ID from local storage
    console.log('Sender ID:', this.senderId);

    if (this.doctorId !== null) {
      this.receiverId = this.doctorId;
      console.log('Receiver ID set in ngOnInit:', this.receiverId);
    }

    this.connectToWebSocket();
    this.loadChat();

    this.messageService.messages$.subscribe((message) => {
      console.log('Received message in component:', message);
      if (message && (message.sender.id === this.receiverId || message.receiver.id === this.senderId)) {
        this.messages.push(message);
      }
    });
  }

  // Detect changes in doctorId and update receiverId dynamically
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['doctorId'] && changes['doctorId'].currentValue !== undefined) {
      this.receiverId = changes['doctorId'].currentValue;
      console.log('Receiver ID updated in ngOnChanges:', this.receiverId);
      this.loadChat(); // Reload chat when doctorId changes
    }
  }

  connectToWebSocket(): void {
    if (this.senderId && this.receiverId) {
      this.messageService.connect(this.senderId);
    }
  }

  loadChat(): void {
    if (this.senderId && this.receiverId) {
      this.messageService.getChat(this.senderId, this.receiverId).subscribe((data) => {
        this.messages = data;
        console.log('Chat loaded:', this.messages);
      });
    }
  }

  sendMessage(): void {
    if (this.newMessage.trim()) {
      console.log('Sending message:', this.newMessage);

      const message: Message = {
        sender: { id: this.senderId },
        receiver: { id: this.receiverId },
        message: this.newMessage
      };

      this.messageService.sendMessageWebSocket(this.senderId, this.receiverId, this.newMessage);
      this.messages.push(message);
      this.newMessage = ''; // Clear input field
    }
  }
}
