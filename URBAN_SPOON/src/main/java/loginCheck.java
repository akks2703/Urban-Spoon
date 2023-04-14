import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class loginCheck extends HttpServlet
{
  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    System.out.println("login service called...");
    //to send the response to the browser
    PrintWriter out = res.getWriter();

    res.setContentType("text/html");

    String email = req.getParameter("email");
    String pword = req.getParameter("pword");

    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");  
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

      String vsql = "select * from logdetails where email=? and pwd=?";

      PreparedStatement pstmt = con.prepareStatement(vsql);
      pstmt.setString(1,email);

      pstmt.setString(2,pword);
      ResultSet rs = pstmt.executeQuery();
        
if( rs.next() ){
        res.sendRedirect("urban_spoon.html");
        
      }else{
        RequestDispatcher rd = req.getRequestDispatcher("login.html");
        rd.include(req,res);
      }
      out.println("</body>");
      out.println("<html>");
      con.close();
    }catch(Exception e){
      res.sendError(500,"Our application is failed due to:" + e);
    }
  }
}