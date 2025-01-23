npm install xlsx jspdf
====================
import { Injectable } from '@angular/core';
import * as XLSX from 'xlsx';
import { jsPDF } from 'jspdf';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor() { }

  // Function to convert Excel to PDF
  convertExcelToPdf(file: File) {
    const reader = new FileReader();

    reader.onload = (e: any) => {
      const abuf = e.target.result;
      const workbook = XLSX.read(abuf, { type: 'array' });

      const doc = new jsPDF();

      // Loop through each sheet in the workbook
      workbook.SheetNames.forEach((sheetName, sheetIndex) => {
        const sheet = workbook.Sheets[sheetName];
        const jsonData = XLSX.utils.sheet_to_json(sheet, { header: 1 });

        // Generate PDF content from sheet
        jsonData.forEach((row:any, rowIndex) => {
          row.forEach((cell:any, colIndex:any) => {
            doc.text(cell.toString(), 10 + colIndex * 40, 10 + rowIndex * 10);
          });
        });

        // Add a new page for each sheet
        if (sheetIndex < workbook.SheetNames.length - 1) {
          doc.addPage();
        }
      });

      // Save the generated PDF
      doc.save('excel-to-pdf.pdf');
    };

    // Read the file as binary string
    reader.readAsArrayBuffer(file);
  }

}


==================
// import { HomeService } from './services/home.service';


constructor(private homeService: HomeService) { }

   // Function to handle file upload
   onFileChange(event: any) {
    const file = event.target.files[0];
    if (file && file.name.endsWith('.xlsx')) {
      this.homeService.convertExcelToPdf(file);
    } else {
      alert('Please upload a valid Excel file');
    }
  }

==================
<div class="file-upload">
    <input type="file" (change)="onFileChange($event)" />
  </div>