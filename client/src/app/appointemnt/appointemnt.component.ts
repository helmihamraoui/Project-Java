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

}
