import { Component } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-patents',
  imports: [NabrapatientsComponent,CommonModule,RouterModule],
  templateUrl: './patents.component.html',
  styleUrl: './patents.component.css'
})
export class PatentsComponent {

}
