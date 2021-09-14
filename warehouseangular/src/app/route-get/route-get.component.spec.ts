import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { RouteGetComponent } from './route-get.component';

describe('RouteGetComponent', () => {
  let component: RouteGetComponent;
  let fixture: ComponentFixture<RouteGetComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ RouteGetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
