package readcsvAssignment2Part4;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.servlet.http.*;


public class ReadcsvAssignment2Part4 extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String requiredFileName = "parking_facilities";
        StringBuilder sb = new StringBuilder();
        String fileName = request.getParameter("xlsFile");
        String fileLoc = "/Users/gaurav/Desktop/Assignment2";
        Properties properties = new Properties();
        String connectionParam = "jdbc:relique:csv:" + fileLoc;
        String query = "SELECT * FROM " + fileName;
        try {
            if (fileName.equals(requiredFileName)) {
                Class.forName("org.relique.jdbc.csv.CsvDriver");
                Connection conn = DriverManager.getConnection(connectionParam, properties);
                Statement executableStmnt = conn.createStatement();
                ResultSet rowData = executableStmnt.executeQuery(query);
                ResultSetMetaData metaData = rowData.getMetaData();
                sb.append("<HTML><HEAD><TITLE>Read CSV USING csv JDBC</TITLE></HEAD><BODY><TABLE BORDER=2px><TR>");
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    //using metadata to print column names
                    sb.append("<TH>" + metaData.getColumnName(i)+"</TH>");
                }
                sb.append("</TR>");
                while (rowData.next()) {
                    //using rowData.next() to move the cursor to next row and making that row as current row
                    sb.append("<TR>");
                    for (int i = 1; i <= columnCount; i++) {
                        //Getting data from ith column in current row.
                        sb.append("<TD>" + rowData.getString(i) + "</TD>");
                    }
                    sb.append("</TR>");
                }
                sb.append("</TABLE>");
                sb.append("</BODY></HTML>");
            } else {
                sb.append("<h1 style=\'text-align:center;\'>PROVIDE PROPER FILE NAME</h1>");
            }
        } catch (SQLException e) {
            sb.append(e);
        } catch (ClassNotFoundException e) {
            sb.append(e);
        }
        out.println(sb);
    }
    public void destroy() {

    }
}