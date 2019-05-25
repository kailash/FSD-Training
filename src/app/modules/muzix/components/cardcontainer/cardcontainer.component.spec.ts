import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardcontainerComponent } from './cardcontainer.component';

xdescribe('CardcontainerComponent', () => {
  let component: CardcontainerComponent;
  let fixture: ComponentFixture<CardcontainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardcontainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardcontainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
