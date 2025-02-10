import { Component } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ChatComponent } from '../chat/chat.component';

@Component({
  selector: 'app-alldoctroforpatents',
  imports: [NabrapatientsComponent,CommonModule,RouterModule,ChatComponent],

  
  templateUrl: './alldoctroforpatents.component.html',
  styleUrl: './alldoctroforpatents.component.css'
})
export class AlldoctroforpatentsComponent {

}
