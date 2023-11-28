import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillBoard100PlaylistComponent } from './bill-board100-playlist.component';

describe('BillBoard100PlaylistComponent', () => {
  let component: BillBoard100PlaylistComponent;
  let fixture: ComponentFixture<BillBoard100PlaylistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BillBoard100PlaylistComponent]
    });
    fixture = TestBed.createComponent(BillBoard100PlaylistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
