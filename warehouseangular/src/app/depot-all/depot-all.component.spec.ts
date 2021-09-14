import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { DepotAllComponent } from './depot-all.component';

describe('DepotAllComponent', () => {
  let component: DepotAllComponent;
  let fixture: ComponentFixture<DepotAllComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ DepotAllComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepotAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
