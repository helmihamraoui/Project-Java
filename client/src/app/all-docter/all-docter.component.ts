import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { AddDocterComponent } from '../add-docter/add-docter.component';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-all-docter',
  imports: [NavbarComponent,AddDocterComponent,RouterModule, FormsModule, CommonModule],
  templateUrl: './all-docter.component.html',
  styleUrl: './all-docter.component.css'
})
export class AllDocterComponent {

  doctors:any[]=[];
  filtredDocters:any[]=[]
  search:String=""
  doctor:any={specialties:"",jobTitle:"",licenseNumb:0,experience:0,user:{firstName:"",lastName:""}}

  constructor(private apiService:ApiService){}
  ngOnInit(){
    this.apiService.getAlldoctors().subscribe(data =>{
      this.doctors=data
      console.log(this.doctors);
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
  details(id:number){
    console.log(id)
    this.apiService.getOneDoctor(id).subscribe(data =>{ this.doctor=data})

  }
}
