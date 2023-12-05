import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';

import { RegistrationFormComponent } from 'src/app/registration-form/registration-form.component';

describe('RegistrationFormComponent', () => {
  let component: RegistrationFormComponent;
  let fixture: ComponentFixture<RegistrationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [RegistrationFormComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize a form with controls', () => {
    expect(component.registrationForm).toBeDefined();
    expect(component.registrationForm.get('firstName')).toBeDefined();
    expect(component.registrationForm.get('lastName')).toBeDefined();
    expect(component.registrationForm.get('email')).toBeDefined();
    expect(component.registrationForm.get('password')).toBeDefined();
  });

  it('should mark the form as invalid if any field is empty', () => {
    component.onSubmit();
    expect(component.registrationForm.invalid).toBe(true);
  });

  it('should mark the form as valid if all fields are filled correctly', () => {
    const form = component.registrationForm;
    form.setValue({
      firstName: 'John',
      lastName: 'Doe',
      email: 'johndoe@example.com',
      password: 'Password@123',
    });

    component.onSubmit();
    expect(form.valid).toBe(true);
  });



  // Add more test cases based on your specific validation requirements
});