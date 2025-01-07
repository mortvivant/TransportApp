package Controllers;

import Views.LogInPage;
import Views.TicketsPage;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportController {

    public static void exportTickets(){

        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm");
            String formattedDateTime = now.format(formatter);

            // PDF'nin kaydedileceği dosya yolu
            String filePath = formattedDateTime + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream(filePath,true));

            document.open();
            document.add(new Paragraph(LogInPage.getUsername() + "'s tickets history"));
            document.add(new Paragraph("\n"));
            PdfPTable pdfPTable = new PdfPTable(TicketsPage.table.getColumnCount());

            for(int i = 0 ; i < TicketsPage.table.getColumnCount();i++){
                pdfPTable.addCell(TicketsPage.table.getColumnName(i));
            }

            for (int j = 0; j < TicketsPage.table.getRowCount();j++){
                for (int k = 0 ; k < TicketsPage.table.getColumnCount(); k++){
                    pdfPTable.addCell(String.valueOf(TicketsPage.table.getValueAt(j,k)));
                }
            }
            document.add(pdfPTable);
            document.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
