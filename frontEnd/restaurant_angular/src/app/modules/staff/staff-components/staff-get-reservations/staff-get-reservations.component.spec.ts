import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffGetReservationsComponent } from './staff-get-reservations.component';

describe('StaffGetReservationsComponent', () => {
  let component: StaffGetReservationsComponent;
  let fixture: ComponentFixture<StaffGetReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StaffGetReservationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StaffGetReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
