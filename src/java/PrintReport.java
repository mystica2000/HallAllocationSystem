import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class PrintReport{
public static void main(String args[])
{
	String dest = "shello.pdf"; 
	PdfWriter writer = null;
	try {
		writer = new PdfWriter(dest);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	PdfDocument pdfDoc = new PdfDocument(writer);
	pdfDoc.addNewPage(); 
	Document document = new Document(pdfDoc); 
	
	float [] pointColumnWidths = {150F, 150F, 150F}; 
	Table table = new Table(pointColumnWidths); 
	
	 table.addCell(getCell("curious"));       
     table.addCell(getCell("hello"));       
     table.addCell(getCell("birthday"));       
     table.addCell(getCell("hugh"));       
     table.addCell(getCell("jackman"));       
     table.addCell(getCell("astro"));    	
	
	document.add(table);
	
	document.close();
}

private static Cell getCell(String cm) {
    Cell cell = new Cell();
      Paragraph p = new Paragraph(cm);
      p.setTextAlignment(TextAlignment.CENTER);
    cell.add(p);
      return cell;
}
}