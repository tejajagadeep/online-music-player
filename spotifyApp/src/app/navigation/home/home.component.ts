import { Component, Renderer2, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private renderer: Renderer2) {}
  ngOnInit() {
    this.loadScript('https://kit.fontawesome.com/cf98ff2373.js');
  }

  private loadScript(scriptUrl: string) {
    const script = this.renderer.createElement('script');
    script.src = scriptUrl;
    script.async = true;
    script.defer = true;

    this.renderer.appendChild(document.body, script);
  }
}
