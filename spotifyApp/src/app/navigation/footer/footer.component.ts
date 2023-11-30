import { Component, Renderer2, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  constructor(private renderer: Renderer2) {}
  ngOnInit() {
    this.loadScript('https://kit.fontawesome.com/7d35781f0a.js');
  }

  private loadScript(scriptUrl: string) {
    const script = this.renderer.createElement('script');
    script.src = scriptUrl;
    script.async = true;
    script.defer = true;

    this.renderer.appendChild(document.body, script);
  }
}
