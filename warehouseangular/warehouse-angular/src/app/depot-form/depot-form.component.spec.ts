import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepotFormComponent } from './depot-form.component';

describe('DepotFormComponent', () => {
  let component: DepotFormComponent;
  let fixture: ComponentFixture<DepotFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepotFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepotFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
