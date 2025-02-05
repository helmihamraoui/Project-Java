import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NabrapatientsComponent } from './nabrapatients.component';

describe('NabrapatientsComponent', () => {
  let component: NabrapatientsComponent;
  let fixture: ComponentFixture<NabrapatientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NabrapatientsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NabrapatientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
