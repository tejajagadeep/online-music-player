// data.service.ts
import { Injectable } from '@angular/core';
import { PlayDialogService } from './play-dialog.service';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private rowData: any[] = []; // Replace with your actual data
  private currentIndex = 0;

  constructor(private dialogService: PlayDialogService) {}

  getNextRowDetails(currentData: any): any {
    const currentIndex = this.rowData.findIndex(item => item === currentData);
    const nextIndex = (currentIndex + 1) % this.rowData.length;
    const nextRowData = this.rowData[nextIndex];
    console.log('Current Index:', currentIndex);
    console.log('Next Index:', nextIndex);
    return nextRowData;
  }

  // openDetailsDialog(data: any): void {
  //   this.dialogService.openDetailsDialog(data);
  // }
}
