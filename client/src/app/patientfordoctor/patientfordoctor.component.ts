import { Component } from '@angular/core';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-patientfordoctor',
  imports: [NavbardocterComponent,CommonModule,FormsModule],
  templateUrl: './patientfordoctor.component.html',
  styleUrl: './patientfordoctor.component.css'
})
export class PatientfordoctorComponent {
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
