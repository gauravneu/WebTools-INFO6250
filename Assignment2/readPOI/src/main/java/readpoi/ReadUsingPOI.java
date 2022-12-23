package readpoi;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import java.util.Iterator;

@WebServlet(name = "readwithPOI", urlPatterns = {"/readwithPOI/store.xls"})
public class ReadUsingPOI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = response.getWriter();

     try {
        FileInputStream xlsFile = new FileInputStream(new File("/Users/gaurav/Desktop/Assignment2/store.xls"));
        HSSFWorkbook hssfWB = new HSSFWorkbook(xlsFile);
        HSSFSheet sh1 = hssfWB.getSheetAt(0);

            sb.append("<HTML><HEAD><TITLE>Read CSV USING POI</TITLE><BODY><TABLE BORDER=1px><TR>");
            Iterator<Row> rw = sh1.iterator();

            //This part of code will get row containing headers and print them
            Row headerRow = rw.next();
            Iterator<Cell> itC = headerRow.cellIterator();
            while (itC.hasNext()) {
                Cell c1 = itC.next();
                sb.append("<TH>" + c1.getStringCellValue()+"</TH>");
            }
              sb.append("</TR>");
            //From here rows will start to get picked and printed
            while (rw.hasNext()) {
                sb.append("<TR>");
                Row nextRow = rw.next();
                Iterator<Cell> icol = nextRow.cellIterator();
                while (icol.hasNext()) {
                    Cell nC = icol.next();
                    if(nC.getCellType() == CellType.BOOLEAN){
                            sb.append("<TD>" + nC.getBooleanCellValue()+"</TD>");
                        }
                        if(nC.getCellType() == CellType.NUMERIC){
                            sb.append("<TD>" + nC.getNumericCellValue()+"</TD>");
                        }
                        if(nC.getCellType() == CellType.STRING){
                            sb.append("<TD>" + nC.getStringCellValue()+"</TD>");
                        }
            }
                sb.append("</TR>");

            }
         sb.append("</TABLE></BODY></HTML>");
         xlsFile.close();
            out.println(sb);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void destroy() {
    }
}