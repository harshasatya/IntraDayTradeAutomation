import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MfstComponent } from './mfst.component';

describe('MfstComponent', () => {
  let component: MfstComponent;
  let fixture: ComponentFixture<MfstComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MfstComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MfstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
