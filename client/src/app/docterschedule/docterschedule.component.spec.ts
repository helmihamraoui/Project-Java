import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocterscheduleComponent } from './docterschedule.component';

describe('DocterscheduleComponent', () => {
  let component: DocterscheduleComponent;
  let fixture: ComponentFixture<DocterscheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DocterscheduleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DocterscheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
