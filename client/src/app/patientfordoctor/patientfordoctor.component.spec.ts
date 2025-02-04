import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientfordoctorComponent } from './patientfordoctor.component';

describe('PatientfordoctorComponent', () => {
  let component: PatientfordoctorComponent;
  let fixture: ComponentFixture<PatientfordoctorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientfordoctorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatientfordoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
