package tuition.tpart6;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

 //@WebServlet(name = "helloServlet", value = "/WaiverForm")
public class TuitionW6 extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

     public void printForm(HttpServletRequest request, HttpServletResponse response) throws IOException{
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         //Section 1:
         String academicTerm = request.getParameter("acdTerm");
         String academicYear = request.getParameter("acdYear");
         String[] empStatus = request.getParameterValues("empStatus");

         //Section 2:
         String studentName = request.getParameter("studentName");
         String empRelationShip = request.getParameter("empRelationShip");
         String stdnuid = request.getParameter("stdnuid");
         String empname = request.getParameter("empname");
         String empnuid = request.getParameter("empnuid");
         String empDept = request.getParameter("empDept");
         String empLoc = request.getParameter("empLoc");
         String phoneNum = request.getParameter("phoneNum");
         String superName = request.getParameter("superName");

         //Section 3:
         String[] status = request.getParameterValues("status");

         String courseNo1 = request.getParameter("courseNo1");
         String courseName1 = request.getParameter("courseName1");
         String supervisorSignature1 = request.getParameter("supervisorSignature1");
         String creditHrs1 = request.getParameter("creditHrs1");
         String Days1 = request.getParameter("Days1");
         String courseTime1 = request.getParameter("time1");




         String courseNo2 = request.getParameter("courseNo2");
         String courseName2 = request.getParameter("courseName2");
         String supervisorSignature2 = request.getParameter("supervisorSignature2");
         String creditHrs2 = request.getParameter("creditHrs2");
         String Days2 = request.getParameter("Days2");
         String courseTime2 = request.getParameter("time2");

         String courseNo3 = request.getParameter("courseNo3");
         String courseName3 = request.getParameter("courseName3");
         String supervisorSignature3 = request.getParameter("supervisorSignature3");
         String creditHrs3 = request.getParameter("creditHrs3");
         String Days3 = request.getParameter("Days3");
         String courseTime3 = request.getParameter("time3");


         //Section 4:
         String empSign = request.getParameter("empSign");
         String DateempSign = request.getParameter("DateempSign");

         //Section 4:
         String hrmApproval = request.getParameter("hrmApproval");
         String DAteHRM = request.getParameter("DAteHRM");

        StringBuilder sb = new StringBuilder();


         sb.append("<doctype HTML>");
         sb.append("<HTML>");
         sb.append("<Head><title>Tuition Form Part 6</title></Head>");
         sb.append("<Body>");
         sb.append("<h2>Section 1</h2>");

         sb.append("<Table border=1px style = center>\n");
         sb.append("<TR><TH>Name</TH><TH>Value</TH></TR>");
         sb.append("<TR><TD>Academic Term</TD><TD>"+academicTerm+"</TD></TR>");
         sb.append("<TR><TD>Academic Year</TD><TD>"+academicYear+"</TD></TR>");
         sb.append("<TR><TD>EMPLOYEE STATUS</TD><TD>");
         for(String s: empStatus){
             sb.append("<li>"+ s+ "</li>");
         }
         sb.append("</TD></TR>");
         sb.append("</Table>");


         sb.append("<h2>Section 2</h2>");
         sb.append("<Table  border=1px style = center>\n");
         sb.append("<TR><TH>Name</TH><TH>Value</TH></TR>");
         sb.append("<TR><TD>Student Name</TD><TD>"+studentName+"</TD></TR>");
         sb.append("<TR><TD>Relationship to Empolyee</TD><TD>"+empRelationShip+"</TD></TR>");
         sb.append("<TR><TD>Student NUID</TD><TD>"+stdnuid+"</TD></TR>");
         sb.append("<TR><TD>Employee Name</TD><TD>"+empname+"</TD></TR>");
         sb.append("<TR><TD>Employee NUID</TD><TD>"+empnuid+"</TD></TR>");
         sb.append("<TR><TD>Department</TD><TD>"+empDept+"</TD></TR>");
         sb.append("<TR><TD>Campus Location</TD><TD>"+empLoc+"</TD></TR>");
         sb.append("<TR><TD>Phone Number</TD><TD>"+phoneNum+"</TD></TR>");
         sb.append("<TR><TD>Supervisor's Name</TD><TD>"+superName+"</TD></TR>");
         sb.append("</Table>");



         sb.append("<h2>Section 3</h2>");
         sb.append("<Table  border=1px style = center>\n");


         sb.append("<TR><TH>Name</TH><TH>Value</TH></TR>");

         sb.append("<TR><TD>Applicable Program</TD><TD>");
         for(String s: status){
             sb.append("<li>"+ s+ "</li>");
         }
         sb.append("</TD></TR>");

         sb.append("<TR><TD>Course No 1</TD><TD>"+courseNo1+"</TD></TR>");
         sb.append("<TR><TD>Course Name 1</TD><TD>"+courseName1+"</TD></TR>");
         sb.append("<TR><TD>Supervisor Signature1</TD><TD>"+supervisorSignature1+"</TD></TR>");
         sb.append("<TR><TD>Credit Hours 1</TD><TD>"+creditHrs1+"</TD></TR>");
         sb.append("<TR><TD>Days 1</TD><TD>"+Days1+"</TD></TR>");
         sb.append("<TR><TD>Course Time 1</TD><TD>"+courseTime1+"</TD></TR>");

         sb.append("<TR><TD>Course No 2</TD><TD>"+courseNo2+"</TD></TR>");
         sb.append("<TR><TD>Course Name 2</TD><TD>"+courseName2+"</TD></TR>");
         sb.append("<TR><TD>Supervisor Signature 2</TD><TD>"+supervisorSignature2+"</TD></TR>");
         sb.append("<TR><TD>Credit Hours 2</TD><TD>"+creditHrs2+"</TD></TR>");
         sb.append("<TR><TD>Days 2</TD><TD>"+Days2+"</TD></TR>");
         sb.append("<TR><TD>Course Time 2</TD><TD>"+courseTime2+"</TD></TR>");

         sb.append("<TR><TD>Course No 3</TD><TD>"+courseNo3+"</TD></TR>");
         sb.append("<TR><TD>Course Name 3</TD><TD>"+courseName3+"</TD></TR>");
         sb.append("<TR><TD>Supervisor Signature 3</TD><TD>"+supervisorSignature3+"</TD></TR>");
         sb.append("<TR><TD>Credit Hours 3</TD><TD>"+creditHrs3+"</TD></TR>");
         sb.append("<TR><TD>Days 3</TD><TD>"+Days3+"</TD></TR>");
         sb.append("<TR><TD>Course Time 3</TD><TD>"+courseTime3+"</TD></TR>");
         sb.append("</Table>");



         sb.append("<h2>Section 4</h2>");
         sb.append("<Table  border=1px style = center>\n");
         sb.append("<TR><TH>Name</TH><TH>Value</TH></TR>");
         sb.append("<TR><TD>Employee Sign</TD><TD>"+empSign+"</TD></TR>");
         sb.append("<TR><TD>Date</TD><TD>"+DateempSign+"</TD></TR>");
         sb.append("</Table>");

         sb.append("<h2>Section 5</h2>");
         sb.append("<Table  border=1px style = center>\n");
         sb.append("<TR><TH>Name</TH><TH>Value</TH></TR>");
         sb.append("<TR><TD>Employee Sign</TD><TD>"+hrmApproval+"</TD></TR>");
         sb.append("<TR><TD>Date</TD><TD>"+DAteHRM+"</TD></TR>");
         sb.append("</Table>");

         sb.append("</Body>");
         sb.append("</HTML>");

         out.println(sb.toString());
   }

     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         printForm(request,response);
     }

     public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
         PrintWriter out = response.getWriter();
         out.println("post");
         printForm(request,response);
     }


     public void destroy() {
    }
}