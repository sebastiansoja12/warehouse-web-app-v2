import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { RouteFormComponent } from './route-form.component';

describe('DepotFormComponent', () => {
  let component: RouteFormComponent;
  let fixture: ComponentFixture<RouteFormComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ RouteFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
