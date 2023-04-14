import java.io.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class reservationCheck extends HttpServlet{

  private static final long serialVersionUID = 1L;
private static final Date Date = null;

  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
//    res.setContentType("text/html");
	  System.out.println("reservation service called...");
	    //to send the response to the browser
	    PrintWriter out = res.getWriter();

	    res.setContentType("text/html");

        String pname = req.getParameter("name");
        String phno=req.getParameter("mobile");
        String pdate = req.getParameter("date");
        String ptime = req.getParameter("time");
        int ppersons=Integer.parseInt(req.getParameter("persons"));
        System.out.println(pname+" "+phno+" "+pdate+" "+ptime+" "+ppersons);
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
            
            String vsql = "insert into reservations(name,phone,pdate,ptime,persons) values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(vsql);
            ps.setString(1,pname);
            ps.setString(2,phno);
            ps.setString(3,pdate);
            ps.setString(4,ptime);
            ps.setInt(5,ppersons);
            ps.executeUpdate();
            res.sendRedirect("s.html");
            ps.close();
        }catch(Exception e){
          PrintWriter out1 = res.getWriter();
            out1.println(e);
        }
  }
}