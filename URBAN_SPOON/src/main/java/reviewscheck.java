import java.io.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class reviewscheck extends HttpServlet{

  private static final long serialVersionUID = 1L;
  private static final Date Date = null;

  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
//    res.setContentType("text/html");
	  System.out.println("reviews service called...");
	    //to send the response to the browser
	    PrintWriter out = res.getWriter();

	    res.setContentType("text/html");

        String name = req.getParameter("name");
        String rest =req.getParameter("rest");
        int rate =Integer.parseInt(req.getParameter("rate"));
        
        
        System.out.println(name+" "+rest+" "+rate);
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
            
            String vsql = "insert into reviews(name,restaurant,rating) values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(vsql);
            ps.setString(1,name);
            ps.setString(2,rest);
            ps.setInt(3,rate);
            ps.executeUpdate();
            res.sendRedirect("getreviews");
            ps.close();
        }catch(Exception e){
          PrintWriter out1 = res.getWriter();
            out1.println(e);
        }
  }
}