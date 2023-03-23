package httpservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayGrocery")

public class DisplayProduct extends HttpServlet{
	Connection con;
	
	@Override
	public void init() throws ServletException {
		
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1emj9", "root", "Dadu@1699");
				
		 }
		 catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		  Statement stmt = null;
	      ResultSet rs = null;
	        // Selection of Query :-
	        String query = "select * from groceryshop";
	        
	        try {
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1emj9", "root", "Dadu@1699");
	            stmt = con.createStatement();
	            rs = stmt.executeQuery(query);
	            
	            PrintWriter pw = resp.getWriter();
	            pw.println("<style>"
	              		+ "body {"
	              		+ "background: linear-gradient(120deg, skyblue, orange);"
	              		+ "font-size: 20px;"
	              		+ "}"
	              		+ "table {"
	              		+ "background: linear-gradient(20deg, pink, lavender, indigo, lavender, pink);"
	              		+ "height: 300px;"
	              		+ "width : 600px;"
	              		+ "margin: 150px;"
	              		+ "}"
	              		+ "th {"
	              		+ "background-color: red;"
	              		+ "</style>");
	            
	            pw.print("<h1> Grocery details : </h1>");
	            pw.print("<table border='1'>");
	              pw.print("<tr>");
	                pw.print("<th> Item ID </th>");
	                pw.print("<th> Item Name </th>");
	                pw.print("<th> Item Stock </th>");
	                pw.print("<th> Item Price </th>");
	              pw.print("</tr>");
	            while (rs.next()) {
	              pw.print("<tr>");
	            	pw.print("<td>"+rs.getInt(1)+    "</td>");
	            	pw.print("<td>"+rs.getString(2)+ "</td>");
	            	pw.print("<td>"+rs.getInt(3)+    "</td>");
	            	pw.print("<td>"+rs.getDouble(4)+ "</td>");
	              pw.print("</tr>");
	            }
	           
	            
	            pw.print("</table>");

	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	       

	
	
	
	}

}
