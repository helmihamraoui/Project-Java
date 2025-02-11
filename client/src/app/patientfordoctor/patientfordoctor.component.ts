import { Component } from '@angular/core';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';
import { firstValueFrom } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patientfordoctor',
  imports: [NavbardocterComponent,CommonModule,FormsModule],
  templateUrl: './patientfordoctor.component.html',
  styleUrl: './patientfordoctor.component.css'
})
export class PatientfordoctorComponent {
    filtredPatients:any[]=[]
    search:String=""
    onePatient:any={emergContact:"",diagnose:{},user:{}}
    doctor:any
    appointments:any[]=[]
    filtredAppointments:any[]=[]
    addData:any={allergies:"",treatments:"",doctors:[]}
    editData:any={allergies:"",treatments:"",doctors:0}

    patientDia:any={emergContact:"",diagnose:{},user:{}}
    diagnoses:any={allergies:"",treatments:"",doctors:[]}
    
      constructor(private apiService:ApiService,private router:Router){}
        ngOnInit() {

        this.apiService.getDoctorByUserId().subscribe(data=>{
          this.doctor=data
        })

        this.apiService.getAppointments().subscribe(data=>{
          this.appointments=data
          this.filtredAppointments=data
        })
      }

      filter(){
        const query=this.search.toLowerCase().trim()
          console.log(this.search)
          this.filtredAppointments=this.appointments.filter(app =>
            app.patient.user.firstname.toLowerCase().includes(query)||
            app.patient.user.lastname.toLowerCase().includes(query)
          )
      }
      
    modal(id:number){
      this.apiService.getOnePatient(id).subscribe(data=>{
        this.onePatient=data
      })
    }

    diagnose(id:number){
      this.apiService.getOnePatient(id).subscribe(data=>{
        this.patientDia=data
      })
    }

    addDiagnose(){
      
      console.log(this.editData)
      if(this.patientDia.diagnose!=null){
        this.editData.doctors=this.doctor.id
        this.apiService.editDiagnose(this.patientDia.diagnose.id,this.editData).subscribe({
          next: (response) => {
            console.log(response);
            this.router.navigate(['/doctor/patients']);
          },
          error: (error) => {
            console.error('Error:', error);
          }
        });
      }else{
        this.addData.doctors.push(this.doctor.id)
        this.apiService.addDiagnose(this.patientDia.id,this.addData).subscribe({
          next: (response) => {
            console.log(response);
            this.router.navigate(['/doctor/patients']);
          },
          error: (error) => {
            console.error('Error:', error);
          }
        });
      }
    }

    display(patient:any){
      this.diagnoses=patient.diagnose
    }
}
