// communication.service.ts
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CommunicationService {
  // Observable string sources
  private methodCallSource = new Subject<void>();

  // Observable string streams
  methodCalled$ = this.methodCallSource.asObservable();

  // Service method to be called
  callMethod() {
    this.methodCallSource.next();
  }
}
