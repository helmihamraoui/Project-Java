import { Component } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ApiService } from '../api.service';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-alldoctroforpatents',
  imports: [NabrapatientsComponent,CommonModule,RouterModule,FormsModule],

  
  templateUrl: './alldoctroforpatents.component.html',
  styleUrl: './alldoctroforpatents.component.css'
})
export class AlldoctroforpatentsComponent {
  doctors:any[]=[];
  filtredDocters:any[]=[]
  search:String=""
  constructor(private apiService:ApiService){}
  ngOnInit(){
    this.apiService.getAlldoctors().subscribe(data =>{
      this.doctors=data
      this.filtredDocters=data
    })
  }
  filter(){
    const query=this.search.toLowerCase().trim()
    console.log(query)
    this.filtredDocters=this.doctors.filter(doctor =>
      doctor.specialties.toLowerCase().includes(query) ||
      doctor.user.firstname.toLowerCase().includes(query)||
      doctor.user.lastname.toLowerCase().includes(query)
    )
}
}
