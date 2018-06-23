/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MyUnlistedComponent } from './my-unlisted.component';

describe('MyUnlistedComponent', () => {
  let component: MyUnlistedComponent;
  let fixture: ComponentFixture<MyUnlistedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyUnlistedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyUnlistedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
