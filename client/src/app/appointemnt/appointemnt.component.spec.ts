import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointemntComponent } from './appointemnt.component';

describe('AppointemntComponent', () => {
  let component: AppointemntComponent;
  let fixture: ComponentFixture<AppointemntComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointemntComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointemntComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
