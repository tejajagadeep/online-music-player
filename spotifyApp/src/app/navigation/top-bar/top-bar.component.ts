import { Component, ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent {

  showDropdown  = false;

  constructor(private el: ElementRef, private renderer: Renderer2){
    
  }

  myData(): void {
    // Add your logic here if needed
  }

  toggleDropdown() {
    this.showDropdown = !this.showDropdown;
    const element = this.el.nativeElement.querySelector('#anotherFunction');
    if (element) {
      this.renderer.addClass(element, 'Active');
    }
  }

}
function myData(): void {
  return;
}

function show(): void {
  const element = document.getElementById('anotherFunction');
  if (element) {
    element.classList.toggle('Active');
  }
}
