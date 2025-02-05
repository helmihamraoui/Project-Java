import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-allpatents',
  imports: [NavbarComponent,RouterModule, FormsModule, CommonModule],
  templateUrl: './allpatents.component.html',
  styleUrl: './allpatents.component.css'
})
export class AllpatentsComponent {
  patients:any[]=[];
  filtredPatients:any[]=[]
  search:String=""
  
    constructor(private apiService:ApiService){}
    ngOnInit(){
      this.apiService.getallPatient().subscribe(data =>{
        this.patients=data
        this.filtredPatients=data
        
      })
    }
    filter(){
      const query=this.search.toLowerCase().trim()
      console.log(query)
      this.filtredPatients=this.patients.filter(patient =>
        patient.user.firstname.toLowerCase().includes(query)||
        patient.user.lastname.toLowerCase().includes(query)
      )
  }
}
