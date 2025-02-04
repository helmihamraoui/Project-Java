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

  constructor(private apiService:ApiService){}
  ngOnInit(){
    this.apiService.getAlldoctors().subscribe(data =>{
      this.doctors=data
      console.log(this.doctors);
      
    })
    console.log(localStorage.getItem("userId"))
    
  }
}
