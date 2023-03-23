package httpservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet ("/PurchaseGrocery")

public class PurchaseProduct extends HttpServlet {
	
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prod_id = req.getParameter("id");
		int ID = Integer.parseInt(prod_id);
		String qty = req.getParameter("quantity");
		int Qty = Integer.parseInt(qty);
		
		PreparedStatement pstmt = null;
	    ResultSet rs = null;

        String query = "select product_price, product_stock from groceryshop where product_id = ?";
	    String query1 = "Update groceryshop set product_stock=? where product_id=?";
	      
		
		  try {
	            pstmt = con.prepareStatement(query);
	            pstmt.setInt(1,ID);
	            rs = pstmt.executeQuery();

	            PrintWriter pw = resp.getWriter();
	            
	            if(rs.next()) {
	                double product_price = rs.getDouble(1);
	                int product_stock = rs.getInt(2);
	                if (Qty<=product_stock) {
	                    double total = product_price*Qty;
	                    
	                    pw.println("<h1>Total Amount = "+total+ "/-</h1>"
	                    		+ "<style>"
	                    		+ "h1 {"
	                    		+ " background-color: red;"
	                    		+ " font-size: 80px;"
	                    		+ " height:    100px;"
	                    		+ " margin:    150px;"
	                    		+ " width:     880px;"
	                    		+ "}"
	                    		+ "body {"
	                    		+ "background-color: yellow;"
	                    		+ "}"                    
	                    		+ "</style>");
	                    pstmt= con.prepareStatement(query1);
	                    pstmt.setInt(1,product_stock-Qty);
	                    pstmt.setInt(2, ID);
	                    int count = pstmt.executeUpdate();
	                    pw.println("<h2>Stock updated Successfully...</h3>"
	                            + "<style>"
	                    		+ "h2 {"
	                    		+ " background-color:green;"
	                    		+ " color: white;"
	                    		+ " font-size: 50px;"
	                    		+ " height:    70px;"
	                    		+ " width:     800px;"
	                    		+ " margin:    150px;"
	                    		+ " border:    3px;"
	                    		+ "}"                    
	                    		+ "</style>");
	                    
	                }
	                else {
	                    pw.println("<h1> Grocery Item units out of Stock </h1>"
	                            + "<style>"
	                    		+ "h3 {"
	                    		+ " background-color: grey;"
	                    		+ " margin:250px;"
	                    		+ " width: 500px;"
	                    		+ "}"                    
	                    		+ "</style>");
	                    		
	                    pw.print("<h1>Stock Available : "+product_stock+ " units</h1>");
	                    pw.print("<style>"
	                    		+  "h3 {"
	                    		+  " background-color: blue;"
	                    		+  " margin:250px;"
	                    		+  " width: 500px;"
	                    		+  "}"
	                    		+ "</style>" );
	                    		
	                }
	            }
	            else {
	            	pw.print("<h1> Item ID not Found </h1>");
	            }

	        } catch (SQLException e) {
	            System.out.println(e);
	        }

		
		
	}
	

}
