import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbardocterComponent } from './navbardocter.component';

describe('NavbardocterComponent', () => {
  let component: NavbardocterComponent;
  let fixture: ComponentFixture<NavbardocterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbardocterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavbardocterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
