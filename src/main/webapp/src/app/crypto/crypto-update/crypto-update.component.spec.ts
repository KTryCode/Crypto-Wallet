import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CryptoUpdateComponent } from './crypto-update.component';

describe('CryptoUpdateComponent', () => {
  let component: CryptoUpdateComponent;
  let fixture: ComponentFixture<CryptoUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CryptoUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CryptoUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
