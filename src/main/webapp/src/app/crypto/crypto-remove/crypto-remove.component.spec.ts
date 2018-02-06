import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CryptoRemoveComponent } from './crypto-remove.component';

describe('CryptoRemoveComponent', () => {
  let component: CryptoRemoveComponent;
  let fixture: ComponentFixture<CryptoRemoveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CryptoRemoveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CryptoRemoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
