import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'millisecondsToMinutes',
  standalone: true
})
export class MillisecondsToMinutesPipe implements PipeTransform {

  transform(milliseconds: number, ...args: unknown[]): string {
    const totalSeconds = Math.floor(milliseconds / 1000); // 1 second = 1000 milliseconds
    const minutes = Math.floor(totalSeconds / 60);
    const seconds = totalSeconds % 60;

    // Use padStart to ensure two-digit formatting
    const formattedMinutes = minutes.toString().padStart(2, '0');
    const formattedSeconds = seconds.toString().padStart(2, '0');

    return `${formattedMinutes}:${formattedSeconds}`;
  }

}
