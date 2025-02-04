import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocterDashbordComponent } from './docter-dashbord.component';

describe('DocterDashbordComponent', () => {
  let component: DocterDashbordComponent;
  let fixture: ComponentFixture<DocterDashbordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DocterDashbordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DocterDashbordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
