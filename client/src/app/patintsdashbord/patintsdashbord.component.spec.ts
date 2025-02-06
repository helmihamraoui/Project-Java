import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatintsdashbordComponent } from './patintsdashbord.component';

describe('PatintsdashbordComponent', () => {
  let component: PatintsdashbordComponent;
  let fixture: ComponentFixture<PatintsdashbordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatintsdashbordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatintsdashbordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
