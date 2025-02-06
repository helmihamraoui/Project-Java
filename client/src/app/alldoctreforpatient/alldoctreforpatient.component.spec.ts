import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlldoctreforpatientComponent } from './alldoctreforpatient.component';

describe('AlldoctreforpatientComponent', () => {
  let component: AlldoctreforpatientComponent;
  let fixture: ComponentFixture<AlldoctreforpatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlldoctreforpatientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlldoctreforpatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
