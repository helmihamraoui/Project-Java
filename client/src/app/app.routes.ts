import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { UrgentComponent } from './urgent/urgent.component';
import { AdminDashbordComponent } from './admin-dashbord/admin-dashbord.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { AddDocterComponent } from './add-docter/add-docter.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { AllpatentsComponent } from './allpatents/allpatents.component';
import { AllDocterComponent } from './all-docter/all-docter.component';
import { DocterDashbordComponent } from './docter-dashbord/docter-dashbord.component';
import { DocterComponent } from './docter/docter.component';
import { AlldocterfordocterComponent } from './alldocterfordocter/alldocterfordocter.component';
import { PatientfordoctorComponent } from './patientfordoctor/patientfordoctor.component';
import { DoctorAppointmentComponent } from './doctor-appointment/doctor-appointment.component';
import { AlldoctroforpatentsComponent } from './alldoctroforpatents/alldoctroforpatents.component';
import { BookingComponent } from './booking/booking.component';
import { AppointemntComponent } from './appointemnt/appointemnt.component';
import { ChatComponent } from './chat/chat.component';
import { LoginDoctorComponent } from './login-doctor/login-doctor.component';
import { PatintsdashbordComponent } from './patintsdashbord/patintsdashbord.component';

export const routes: Routes = [     
     { path: '', component: HomeComponent },  
     { path: 'login', component: LoginComponent },  
     { path: 'signup', component: SignUpComponent },  
     { path: 'urgent', component: UrgentComponent
     },  
     { path: 'add/doctor', component:LoginDoctorComponent
     },
     //for the admin
     { path: 'admin/dashbord', component:AdminDashbordComponent }, 
     { path: 'amdin/schedule', component:ScheduleComponent } ,
     { path: 'all/patients', component:AllpatentsComponent } ,
     { path: 'admin/appointments', component:AppointmentsComponent } , 
     { path: 'all/doctor', component:AllDocterComponent } ,   
     //for the doctor
     { path: 'doctor/dashbord', component:DocterDashbordComponent } ,   
     { path: 'doctor/schedule', component:DocterComponent } ,  
     { path: 'doctor/doctor', component:AlldocterfordocterComponent } ,    
     { path: 'doctor/patients', component:PatientfordoctorComponent } ,  
     { path: 'doctor/appointment', component:DoctorAppointmentComponent } ,    
     //for patents   
     { path:'patient/booking/:id',component:BookingComponent},
     { path:'patient/dashbord',component:PatintsdashbordComponent},
     { path: 'patient/alldoctor', component:AlldoctroforpatentsComponent } ,     
     { path:'pation',component:AppointemntComponent},
     { path: 'chat/:receiverId', component:ChatComponent } ,
     { path: 'add/docter', component:LoginDoctorComponent } ,
     { path: 'addocter', component:AddDocterComponent } ,


];
