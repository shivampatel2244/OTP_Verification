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

@WebServlet("/InsertdataServlet")
public class InsertdataServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();	
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","shiv");
			PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?,?)");
			
			int Rollno = Integer.parseInt(request.getParameter("sroll"));
			String Name = request.getParameter("sname");
			String City = request.getParameter("scity");
			int Marks = Integer.parseInt(request.getParameter("smarks"));

			ps.setInt(1, Rollno);
			ps.setString(2, Name);
			ps.setString(3, City);
			ps.setInt(4, Marks);
			
			int i = ps.executeUpdate();
			if(i > 0)
			{
				out.print("<script>alert('Record Inserted Successfully');</script>");
				response.sendRedirect("./Main.html");
//				rd = request.getRequestDispatcher("./Main.html");
//				rd.forward(request,response);
			}
			else
			{
				out.print("<script>alert('Record Not Inserted....!');</script>");
//				rd = request.getRequestDispatcher("./Insert.html");
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
