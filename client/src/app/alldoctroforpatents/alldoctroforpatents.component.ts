import { ChatComponent } from './../chat/chat.component';
import { Component, EventEmitter, Output } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ApiService } from '../api.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-alldoctroforpatents',
  imports: [NabrapatientsComponent, CommonModule, RouterModule, ChatComponent, FormsModule],
  templateUrl: './alldoctroforpatents.component.html',
  styleUrls: ['./alldoctroforpatents.component.css']
})
export class AlldoctroforpatentsComponent {
  doctors: any[] = [];
  filtredDocters: any[] = [];
  search: String = '';
  selectedDoctorId: number =0; // Store selected doctor's ID
  @Output() selectedDoctor = new EventEmitter<number>();
  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getAlldoctors().subscribe((data) => {
      this.doctors = data;
      this.filtredDocters = data;
    });
  }

  filter() {
    const query = this.search.toLowerCase().trim();
    this.filtredDocters = this.doctors.filter((doctor) =>
      doctor.user.firstName.toLowerCase().includes(query) ||
      doctor.specialties.toLowerCase().includes(query) ||
      doctor.user.lastName.toLowerCase().includes(query)
    );
  }

  chat(doctorId: number) {
    console.log('Chat with doctor:', doctorId);
    this.selectedDoctorId = doctorId; // Set the ID when chat button is clicked
  console.log('Chat with doctor:', this.selectedDoctorId);
  }
}
