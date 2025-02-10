import { Component } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ChatComponent } from '../chat/chat.component';
import { ApiService } from '../api.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-alldoctroforpatents',
  imports: [NabrapatientsComponent,CommonModule,RouterModule,ChatComponent,FormsModule],



  
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
      doctor.user.firstName.toLowerCase().includes(query) ||
      doctor.specialties.toLowerCase().includes(query) ||
      doctor.user.lastName.toLowerCase().includes(query)
    )
}
}
