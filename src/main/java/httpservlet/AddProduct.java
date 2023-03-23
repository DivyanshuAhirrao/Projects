package httpservlet;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddGrocery")

public class AddProduct extends HttpServlet{
	Connection con;
	
	@Override
	public void init() throws ServletException {
    	
	    try {
			
			Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1emj9", "root", "Dadu@1699");
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}	

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		// Fetch the values from html :
	
		String prod_name    = req.getParameter("name");
		String prod_stock   = req.getParameter("stock");
		String prod_price   = req.getParameter("price");
		
		//parse the String values 
		
		int stock = Integer.parseInt(prod_stock);
		double price = Double.parseDouble(prod_price);
		
		//declare resources :
		PreparedStatement pstmt = null;
		
		//create a Query :
		String query = "insert into groceryshop(product_name, product_stock, product_price) values(?,?,?)";
		
		
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, prod_name);
			pstmt.setInt(2, stock); 	
			pstmt.setDouble(3, price);
			int count = pstmt.executeUpdate();
			 
			PrintWriter pw = resp.getWriter();
			pw.print("<h1>" +count+ " Record Inserted SuccessFully...</h1>");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
