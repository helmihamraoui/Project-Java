import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlldoctroforpatentsComponent } from './alldoctroforpatents.component';

describe('AlldoctroforpatentsComponent', () => {
  let component: AlldoctroforpatentsComponent;
  let fixture: ComponentFixture<AlldoctroforpatentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlldoctroforpatentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlldoctroforpatentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
