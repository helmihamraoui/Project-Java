import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlldocterfordocterComponent } from './alldocterfordocter.component';

describe('AlldocterfordocterComponent', () => {
  let component: AlldocterfordocterComponent;
  let fixture: ComponentFixture<AlldocterfordocterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlldocterfordocterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlldocterfordocterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
