import { Component } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-alldoctroforpatents',
  imports: [NabrapatientsComponent,CommonModule,RouterModule],

  
  templateUrl: './alldoctroforpatents.component.html',
  styleUrl: './alldoctroforpatents.component.css'
})
export class AlldoctroforpatentsComponent {

}
