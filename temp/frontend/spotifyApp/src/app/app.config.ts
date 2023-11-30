import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), 
    provideHttpClient(withFetch()), 
    provideClientHydration(), 
    provideAnimations(),
    MatTableModule
  ]
};
