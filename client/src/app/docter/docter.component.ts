import { Component } from '@angular/core';
import { NavbardocterComponent } from '../navbardocter/navbardocter.component'; 
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ApiService } from '../api.service';
import { forkJoin, switchMap } from 'rxjs';

@Component({
  selector: 'app-docter',
  imports: [NavbardocterComponent, RouterModule, FormsModule, CommonModule],
  templateUrl: './docter.component.html',
  styleUrls: ['./docter.component.css']
})
export class DocterComponent {
  currentYear: number = new Date().getFullYear();
  currentMonth: number = new Date().getMonth();
  today: Date = new Date();
  selectedDate: string = '';
  availableTimes: string[] = [];
  listOfWeeks: { day: number | null, date: Date | null, isPast: boolean, isAvailable: boolean, appointments: any[] }[][] = [];
  doctorId: number = 1;  // Doctor ID should be retrieved from localStorage
  appointmentsDoctor: any[] = [];
  selectedTime: string = '';
  doctor:any={}
  months = [
    { value: 0, name: 'January' }, { value: 1, name: 'February' },
    { value: 2, name: 'March' }, { value: 3, name: 'April' },
    { value: 4, name: 'May' }, { value: 5, name: 'June' },
    { value: 6, name: 'July' }, { value: 7, name: 'August' },
    { value: 8, name: 'September' }, { value: 9, name: 'October' },
    { value: 10, name: 'November' }, { value: 11, name: 'December' }
  ];

  constructor(private apiService: ApiService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    // First, retrieve the doctorId from local storage or the API
    this.apiService.getDoctorByUserId().pipe(
      switchMap(data => {
        // Save the doctorId
        this.doctorId = data.id;
        console.log("**** Doctor ID: ", this.doctorId);
  
        // Now that we have the doctorId, fetch appointments for the doctor
        return this.apiService.getAppointmentByDoctorId(this.doctorId);
      })
    ).subscribe(appointments => {
      // Now we have the appointments data, so we can process it
      this.appointmentsDoctor = appointments;
      console.log("Fetched Appointments:", this.appointmentsDoctor);
  
      // Once appointments are fetched, generate the calendar days
      this.generateCalendarDays();
    }, error => {
      // Handle error if any of the API calls fail
      console.error("Error fetching data:", error);
    });
  }

  // Fetch appointments for the doctor
  getDoctorAppointments() {
    this.apiService.getAppointmentByDoctorId(this.doctorId).subscribe((data: any[]) => {
      this.appointmentsDoctor = data;
      console.log("Fetched Appointments:", this.appointmentsDoctor);
      this.generateCalendarDays(); // Regenerate calendar days after fetching appointments
    });
  }

  // Generate the calendar days with available times and appointments
  generateCalendarDays() {
    this.listOfWeeks = [];
    const firstDayOfMonth = new Date(this.currentYear, this.currentMonth, 1).getDay();
    const daysInMonth = new Date(this.currentYear, this.currentMonth + 1, 0).getDate();
    let week: { day: number | null, date: Date | null, isPast: boolean, isAvailable: boolean, appointments: any[] }[] = [];
  
    // Add empty days before the first valid day of the month
    for (let i = 0; i < firstDayOfMonth; i++) {
      week.push({ day: null, date: null, isPast: false, isAvailable: false, appointments: [] });
    }
  
    // Loop through the days in the month
    for (let i = 1; i <= daysInMonth; i++) {
      const currentDate = new Date(this.currentYear, this.currentMonth, i);
      const currentDateString = currentDate.toISOString().split('T')[0];  // Format as YYYY-MM-DD
  
      // Get appointments for the current date
      const appointmentsForDay = this.appointmentsDoctor.filter(app => {
        const appDate = new Date(app.time);
        const appYear = appDate.getFullYear();
        const appMonth = appDate.getMonth();
        const appDay = appDate.getDate();
  
        const currentYear = currentDate.getFullYear();
        const currentMonth = currentDate.getMonth();
        const currentDay = currentDate.getDate();
  
        // Compare only the year, month, and day
        return appYear === currentYear && appMonth === currentMonth && appDay === currentDay;
      });
  
      const isAvailable = !appointmentsForDay.length;
  
      week.push({
        day: i,
        date: currentDate,
        isPast: currentDate < this.today,
        isAvailable,
        appointments: appointmentsForDay
      });
  
      // If a week has 7 days, push it into the list of weeks
      if (week.length === 7) {
        this.listOfWeeks.push(week);
        week = [];
      }
    }
  
    // Fill the remaining empty cells for the last week
    while (week.length < 7) {
      week.push({ day: null, date: null, isPast: false, isAvailable: false, appointments: [] });
    }
  
    // Add the last week to the list
    if (week.length > 0) {
      this.listOfWeeks.push(week);
    }
  }
  
  
  

  // Select a month from the dropdown
  selectMonth(event: any) {
    this.currentMonth = parseInt(event.target.value, 10);
    this.generateCalendarDays();
  }

  // Select a date from the calendar
  selectDate(day: number | null) {
    if (day !== null) {
      const selected = new Date(this.currentYear, this.currentMonth, day);
      const year = selected.getFullYear();
      const month = (this.currentMonth).toString().padStart(2, '0');
      const dayString = day.toString().padStart(2, '0');

      this.selectedDate = `${year}-${month}-${dayString}`;
      this.selectedTime = ''; // Clear the selected time when a new date is picked
      console.log(`Selected Date: ${this.selectedDate}`);
    }
  }

 
}
