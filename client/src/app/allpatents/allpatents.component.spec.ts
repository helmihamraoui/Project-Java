import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllpatentsComponent } from './allpatents.component';

describe('AllpatentsComponent', () => {
  let component: AllpatentsComponent;
  let fixture: ComponentFixture<AllpatentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllpatentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllpatentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
