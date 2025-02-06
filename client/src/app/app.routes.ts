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
import { PatentsComponent } from './patents/patents.component';
import { AppointemntComponent } from './appointemnt/appointemnt.component';
import { ChatComponent } from './chat/chat.component';

export const routes: Routes = [     
     { path: '', component: HomeComponent },  
     { path: 'login', component: LoginComponent },  
     { path: 'signup', component: SignUpComponent },  
     { path: 'urgent', component: UrgentComponent},   
     //for the admin
     { path: 'admildashb', component:AdminDashbordComponent }, 
     { path: 'schedule', component:ScheduleComponent } ,
     { path: 'allpatents', component:AllpatentsComponent } ,
     { path: 'appointments', component:AppointmentsComponent } , 
     { path: 'alldocter', component:AllDocterComponent } ,   
     //for the doctor
     { path: 'docterdashborder', component:DocterDashbordComponent } ,   
     { path: 'doctorschedule', component:DocterComponent } ,  
     { path: 'allfordoctor', component:AlldocterfordocterComponent } ,    
     { path: 'patietfordoctor', component:PatientfordoctorComponent } ,  
     { path: 'doctorappointment', component:DoctorAppointmentComponent } ,  
     //for patients 
     { path: 'dalldoctreforpa', component:AlldoctroforpatentsComponent } ,     
     { path:'booking/:id',component:BookingComponent},
     { path:'pation',component:AppointemntComponent},

     { path: 'addocter', component:AddDocterComponent } ,
     


     { path: 'chat/:receiverId', component:ChatComponent } ,
     { path: 'addocter', component:AddDocterComponent } ,
]; 

