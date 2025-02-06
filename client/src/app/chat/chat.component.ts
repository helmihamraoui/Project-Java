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
  senderId: number = 0;
  receiverId: number = 0;
  messages: Message[] = [];
  newMessage: string = '';

  constructor(private messageService: MessageService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.senderId = Number(localStorage.getItem('userId'));

    this.route.paramMap.subscribe(params => {
      this.receiverId = Number(params.get('receiverId'));
      console.log('Sender ID:', this.senderId);
      console.log('Receiver ID:', this.receiverId);

      this.connectToWebSocket();
      this.loadChat();
    });

    this.messageService.messages$.subscribe((message) => {
      console.log('Received message in component:', message);
      if (message && (message.sender.id === this.receiverId || message.receiver.id === this.senderId)) {
        this.messages.push(message);
      }
    });
  }

  connectToWebSocket(): void {
    this.messageService.connect(this.senderId);
    this.messageService.connect(this.receiverId);
  }

  loadChat(): void {
    this.messageService.getChat(this.senderId, this.receiverId).subscribe((data) => {
      this.messages = data;
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
      this.messageService.sendMessageWebSocket(this.senderId, this.receiverId, this.newMessage);
  
      this.messages.push(message);
      this.newMessage = '';
    }
  }
}
