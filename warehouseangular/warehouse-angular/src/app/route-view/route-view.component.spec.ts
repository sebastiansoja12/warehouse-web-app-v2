import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RouteViewComponent } from './route-view.component';

describe('DepotViewComponent', () => {
  let component: RouteViewComponent;
  let fixture: ComponentFixture<RouteViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RouteViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
