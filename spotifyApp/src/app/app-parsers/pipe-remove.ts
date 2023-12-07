// remove-time.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'removeTime'
})
export class RemoveTimePipe implements PipeTransform {
  transform(value: any): any {
    if (value === null) {
      return 'NA';
    }
    // Parse the input string to a Date object
    const dateObject = new Date(value);

    // Use toISOString to get the date in "YYYY-MM-DD" format
    const datePart = dateObject.toISOString().split('T')[0];

    return datePart;
  }
}
