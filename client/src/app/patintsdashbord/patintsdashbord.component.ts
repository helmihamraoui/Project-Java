import { Component } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-patintsdashbord',
  imports: [NabrapatientsComponent,CommonModule,RouterModule],
  templateUrl: './patintsdashbord.component.html',
  styleUrl: './patintsdashbord.component.css'
})
export class PatintsdashbordComponent {

}
