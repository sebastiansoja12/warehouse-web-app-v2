import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { RouteDeleteComponent } from './route-delete.component';

describe('RouteDeleteComponent', () => {
  let component: RouteDeleteComponent;
  let fixture: ComponentFixture<RouteDeleteComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ RouteDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
