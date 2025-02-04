import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllDocterComponent } from './all-docter.component';

describe('AllDocterComponent', () => {
  let component: AllDocterComponent;
  let fixture: ComponentFixture<AllDocterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllDocterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllDocterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
