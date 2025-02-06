import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleforpatinentComponent } from './scheduleforpatinent.component';

describe('ScheduleforpatinentComponent', () => {
  let component: ScheduleforpatinentComponent;
  let fixture: ComponentFixture<ScheduleforpatinentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ScheduleforpatinentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleforpatinentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
