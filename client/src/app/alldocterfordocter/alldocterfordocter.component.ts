import { Component } from '@angular/core';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-alldocterfordocter',
  imports: [NavbardocterComponent,CommonModule,FormsModule,RouterModule],
  templateUrl: './alldocterfordocter.component.html',
  styleUrl: './alldocterfordocter.component.css'
})
export class AlldocterfordocterComponent {
    doctors:any[]=[];
    filtredDocters:any[]=[]
    search:String=""
  
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
          doctor.user.firstName.toLowerCase().includes(query)||
          doctor.user.lastName.toLowerCase().includes(query)
        )
    }
}
