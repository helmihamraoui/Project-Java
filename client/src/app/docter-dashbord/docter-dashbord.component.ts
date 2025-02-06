import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-docter-dashbord',
  imports: [NavbardocterComponent,CommonModule],
  templateUrl: './docter-dashbord.component.html',
  styleUrl: './docter-dashbord.component.css'
})
export class DocterDashbordComponent {
    patients:any[]=[]
    doctors:any[]=[]
    
  
    constructor(private apiService:ApiService){}

    ngOnInit(){
      this.apiService.getAlldoctors().subscribe(data=>{
        this.doctors=data
      })
      this.apiService.getallPatient().subscribe(data=>{
        this.patients=data
      })
    }
}
