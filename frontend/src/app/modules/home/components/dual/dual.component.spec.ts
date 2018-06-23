/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { DualComponent } from './dual.component';

describe('DualComponent', () => {
  let component: DualComponent;
  let fixture: ComponentFixture<DualComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DualComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
