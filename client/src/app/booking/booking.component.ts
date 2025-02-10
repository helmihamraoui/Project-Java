import { Component, OnInit } from '@angular/core';
import { NabrapatientsComponent } from '../nabrapatients/nabrapatients.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [NabrapatientsComponent, RouterModule, FormsModule, CommonModule],
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  currentYear: number = new Date().getFullYear();
  currentMonth: number = new Date().getMonth();
  today: Date = new Date();
  selectedDate: string = '';
  availableTimes: string[] = [];
  listOfWeeks: { day: number | null, date: Date | null, isPast: boolean, isAvailable: boolean }[][] = [];
  doctorId: number = 1;  // Set the doctorId as required
  appointmentsDoctor: any[] = [];
  selectedTime: string = '';
  PatientId: number = 0; // Set the patientId as required
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
    //get pation with user id
    this.apiService.getPatientByUserId().subscribe((data: any) => {
      this.PatientId = data.id;
    });

    //get doctor id from the route
    this.route.params.subscribe(params => {
      this.doctorId = Number(params['id']);
    });
    this.getDoctorAppointments();
    this.generateCalendarDays();
  }

  generateCalendarDays() {
    this.listOfWeeks = [];
    const firstDayOfMonth = new Date(this.currentYear, this.currentMonth, 1).getDay();
    const daysInMonth = new Date(this.currentYear, this.currentMonth + 1, 0).getDate();
    let week: { day: number | null, date: Date | null, isPast: boolean, isAvailable: boolean }[] = [];

    for (let i = 0; i < firstDayOfMonth; i++) {
      week.push({ day: null, date: null, isPast: false, isAvailable: false });
    }

    for (let i = 1; i <= daysInMonth; i++) {
      const currentDate = new Date(this.currentYear, this.currentMonth, i);
      const todayWithoutTime = new Date(this.today.getFullYear(), this.today.getMonth(), this.today.getDate());
      const isPast = currentDate < todayWithoutTime;
      const currentDateString = currentDate.toISOString().split('T')[0];
      const hasAppointments = this.appointmentsDoctor.some(app => app.date === currentDateString);
      const isAvailable = !hasAppointments && !isPast;

      week.push({ day: i, date: currentDate, isPast, isAvailable });

      if (week.length === 7) {
        this.listOfWeeks.push(week);
        week = [];
      }
    }

    while (week.length < 7) {
      week.push({ day: null, date: null, isPast: false, isAvailable: false });
    }

    if (week.length > 0) {
      this.listOfWeeks.push(week);
    }
  }

  selectMonth(event: any) {
    this.currentMonth = parseInt(event.target.value, 10);
    this.generateCalendarDays();
  }

  selectDate(day: number | null) {
    if (day !== null) {
      const selected = new Date(this.currentYear, this.currentMonth, day);
      const year = selected.getFullYear();
      const month = (this.currentMonth + 1).toString().padStart(2, '0');
      const dayString = day.toString().padStart(2, '0');

      this.selectedDate = `${year}-${month}-${dayString}`;
      this.selectedTime = ''; // Clear the selected time when a new date is picked
      console.log(`Selected Date: ${this.selectedDate}`);

      this.getAvailableTimes();
    }
  }

  getDoctorAppointments() {
    this.apiService.getAppointmentByDoctorId(this.doctorId).subscribe((data: any[]) => {
      this.appointmentsDoctor = data;
      console.log("Doctor's Appointments:", this.appointmentsDoctor); // Debugging output
      this.generateCalendarDays(); // Regenerate calendar days after fetching appointments
    });
  }

  getAvailableTimes() {
    if (this.selectedDate) {
      const selectedDate = new Date(this.selectedDate);
      const selectedDateString = selectedDate.toISOString().split('T')[0]; // Get YYYY-MM-DD

      // Get all appointments for the selected date
      const appointments = this.appointmentsDoctor.filter((app: any) => {
        console.log("*****",app.time.split('T')[0]);
        return app.time && app.time.split('T')[0] === selectedDateString;
      });

      // Extract the reserved times from the appointments
      let reservedTimes = appointments.map((app: any) => {
        if (app.time) {
          try {
            return app.time.split('T')[1].slice(0, 5);  // Extract time part (HH:mm)
          } catch (e) {
            console.error('Invalid time format for appointment:', app.time);
            return '';  // Return empty string if there is an error in time format
          }
        } else {
          console.error('Missing time for appointment:', app);
          return '';  // Return empty string if time is missing
        }
      });

      // All possible time slots
      const allTimes = ['09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00'];

      // Filter out reserved times from available times
      this.availableTimes = allTimes.filter(time => !reservedTimes.includes(time));

      console.log("Available times after filtering:", this.availableTimes);  // Debugging output
    }
  }
  submitAppointment() {
    if (this.selectedDate && this.selectedTime) {
      const appointmentData = {
        doctor:{id: this.doctorId},
        patient:{id: this.PatientId}, // Set the patientId as required
        time: `${this.selectedDate}T${this.selectedTime}:00`,
      };
      console.log('Appointment Data:', appointmentData);
      // Call API to submit appointment data
      this.apiService.addAppointment(appointmentData).subscribe((response: any) => {
        console.log('Appointment submitted successfully:', response);
        this.router.navigate(['/patient/dashbord']);  // Redirect to appointments page after successful submission
      });
    } else {
      console.error('Please select a date and time for the appointment.');
    }
  }

}
