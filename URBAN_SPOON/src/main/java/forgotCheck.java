import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class forgotCheck extends HttpServlet
{
  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    System.out.println("service called...");
    //to send the response to the browser
    PrintWriter out = res.getWriter();

    res.setContentType("text/html");

 
    
    String resetpwd = req.getParameter("password");
    String conformpwd=req.getParameter("cpwd");

    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

      String vsql = "select * from forgotdetails where   resetpwd=? and cpwd=?";

      PreparedStatement pstmt = con.prepareStatement(vsql);
     
      pstmt.setString(1, resetpwd);
      pstmt.setString(2,conformpwd);
      ResultSet rs = pstmt.executeQuery();
        
if( rs.next() ){
        req.setAttribute("cpwd",conformpwd);
        RequestDispatcher rd = req.getRequestDispatcher("login.html");
        rd.forward(req,res);
        
      }else{
        out.println("Invalid username/password<br>");
        RequestDispatcher rd = req.getRequestDispatcher("forgotpassword.html");
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
