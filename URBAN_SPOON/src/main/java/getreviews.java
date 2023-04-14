import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class getreviews extends HttpServlet{

  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
    res.setContentType("text/html");
      PrintWriter out = res.getWriter();
      out.println("<html>");
      out.println("<head><title>Check Reviews</title></head>");
      out.println("<body>");
      out.println("<center><h1>Check reviews</h1>");
    try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
           
            Statement st = con.createStatement();
  
            ResultSet rs =  st.executeQuery("select * from reviews");
            PrintWriter out1 = res.getWriter();
            out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>Name</th><th>Restaurant</th><th>Rating</th></tr>");
            while(rs.next()) {
              String name = rs.getString("name");
              String rest = rs.getString("restaurant");
              int rate = rs.getInt("rating");
              

              out1.println("<tr><td>" + name + "</td><td>" + rest + "</td><td>" + rate + "</td></tr>");   
            }
            out1.println("</table>"); 
            out1.println("<br><br>");
            out1.println("<form id=\"form\" class=\"form\" action=\"urban_spoon.html\">");
            out1.println("<button type=\"submit\" value=\"Back to home page\">Back to home page</button>");
            out1.println("</html></body>");  
            out.close();
        }
    catch(Exception e){
          PrintWriter out1 = res.getWriter();
            out1.println(e);
        }
   
    
  }
}
