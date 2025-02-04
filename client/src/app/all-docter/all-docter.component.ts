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

  constructor(private apiService:ApiService){}
  ngOnInit(){
    this.apiService.getallPatient().subscribe(data =>{
      this.doctors=data
      console.log(this.doctors);
      this.filtredDocters=data
    })
    
  }
  filter(){
      const query=this.search.toLowerCase().trim()
      console.log(query)
      this.filtredDocters=this.doctors.filter(doctor =>
        doctor.user.firstname.toLowerCase().includes(query)||
        doctor.user.lastname.toLowerCase().includes(query)
      )
  }
}
