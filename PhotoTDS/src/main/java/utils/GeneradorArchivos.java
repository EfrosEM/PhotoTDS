package utils;

import java.io.*;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dominio.Usuario;

public class GeneradorArchivos {

	@SuppressWarnings("resource")
	public static void generarExcel(Usuario user) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "Seguidores");

        String[] headers = new String[]{
                "Nombre",
                "Email",
                "Presentación"
        };
        
        List<Usuario> seguidores = user.getSeguidores();
        
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }
        
        int i = 0;

        for (Usuario seguidor : seguidores) {
            HSSFRow dataRow = sheet.createRow(i + 1);
            
            String nombre = seguidor.getUsuario();
            String email = seguidor.getEmail();
            String presentacion = seguidor.getDescripcion();

            dataRow.createCell(0).setCellValue(nombre);
            dataRow.createCell(1).setCellValue(email);
            dataRow.createCell(2).setCellValue(presentacion);
            
            i++;
        }

        FileOutputStream file = new FileOutputStream("seguidores.xls");
        workbook.write(file);
        file.close();
    }
	
	public static void generarPDF(Usuario user) throws FileNotFoundException, DocumentException {
	
		Document document = new Document();
		List<Usuario> seguidores = user.getSeguidores();
		 
        try {
        	String path = new File(".").getCanonicalPath();
        	String FILE_NAME = path + "/seguidores.pdf";
        	
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
 
            document.open();
 
            PdfPTable table = new PdfPTable(3); 
            table.addCell("Nombre");
            table.addCell("Email");
            table.addCell("Presentación");
            
            for (Usuario seguidor : seguidores) {
            	table.addCell(seguidor.getUsuario());
                table.addCell(seguidor.getEmail());
                table.addCell(seguidor.getDescripcion());
            }
 
            document.add(table);
            document.close();
 
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
