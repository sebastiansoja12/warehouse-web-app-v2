import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepotViewComponent } from './depot-view.component';

describe('DepotViewComponent', () => {
  let component: DepotViewComponent;
  let fixture: ComponentFixture<DepotViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepotViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepotViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
