package p1;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();	
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","shiv");
			PreparedStatement ps = con.prepareStatement("UPDATE student SET name=?, city=?, total=? WHERE rollno = ?");
			
			String Name = request.getParameter("sname");
			String City = request.getParameter("scity");
			int Marks = Integer.parseInt(request.getParameter("smarks"));
			int Rollno = Integer.parseInt(request.getParameter("sroll"));
			
			
			ps.setString(1, Name);
			ps.setString(2, City);
			ps.setInt(3, Marks);
			ps.setInt(4, Rollno);
			
			int i = ps.executeUpdate();
			if(i > 0)
			{
				out.print("<script>alert('Record Updated Successfully');</script>");
				response.sendRedirect("./Main.html");
//				rd = request.getRequestDispatcher("./Main.html");
//				rd.forward(request,response);
			}
			else
			{
				out.print("<script>alert('Record Not Updated....!');</script>");
//				rd = request.getRequestDispatcher("/Main.html");
//				rd.include(request, response);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
