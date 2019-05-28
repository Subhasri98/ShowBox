/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author welcome
 */
public class registerservlet extends HttpServlet {

   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
        try{
      String username = request.getParameter("uname");
      String password = request.getParameter("psw");
      String city = request.getParameter("city");
      String state = request.getParameter("state");
      String country = request.getParameter("country");
      String contact = request.getParameter("contact");
      String email = request.getParameter("email");
      out.println(username);
      out.println(password);
      Class.forName("org.apache.derby.jdbc.ClientDriver");
Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/registration","root","root");
      PreparedStatement pst = con.prepareStatement("insert into registration values(?,?,?,?,?,?,?)");
      pst.setString(1,username);
      pst.setString(2,password);
      pst.setString(3,city);
      pst.setString(4,state);
      pst.setString(5,country);
      pst.setString(6,contact);
      pst.setString(7,email);
      int i = pst.executeUpdate();
      if(i!=0){
       // out.println("<br>Record has been inserted");
        String site = new String("index.html");

      response.setStatus(response.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", site);    
       
      }
      else{
        out.println("failed to insert the data");
      }
      con.close();
    }
    catch (Exception e){
      out.println(e);
    }
  }

}
