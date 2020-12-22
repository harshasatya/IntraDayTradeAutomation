import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AaplComponent } from './aapl.component';

describe('AaplComponent', () => {
  let component: AaplComponent;
  let fixture: ComponentFixture<AaplComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AaplComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AaplComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
