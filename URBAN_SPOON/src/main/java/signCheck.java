import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class signCheck extends HttpServlet
{
  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    System.out.println("signup service called...");
    //to send the response to the browser
    PrintWriter out = res.getWriter();

    res.setContentType("text/html");

 
    String email = req.getParameter("email");
    String pwd = req.getParameter("pwd");

    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

      String vsql = "insert into logdetails(email,pwd) values(?,?)";

      PreparedStatement pstmt = con.prepareStatement(vsql);
      pstmt.setString(1,email);
      pstmt.setString(2, pwd);
      pstmt.executeUpdate();
      res.sendRedirect("login.html");
      
    }catch(Exception e){
      res.sendError(500,"Our application is failed due to:" + e);
    }
  }
}




 