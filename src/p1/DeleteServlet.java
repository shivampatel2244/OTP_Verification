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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();	
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","shiv");
			PreparedStatement ps = con.prepareStatement("delete student where rollno = ?");
			
			int Rollno = Integer.parseInt(request.getParameter("sroll"));

			ps.setInt(1, Rollno);
			
			int i = ps.executeUpdate();
			if(i > 0)
			{
				out.print("<script>alert('Record Deleted Successfully');</script>");
				response.sendRedirect("./AllDataServlet");
//				out.print("<script>function myFunction() {  let text = 'Press a button!\nEither OK or Cancel.'; if (confirm(text) == true) {    text = 'You pressed OK!';  } else {    text = 'You canceled!';  }  document.getElementById('demo').innerHTML = text;}</script>");
//				rd = request.getRequestDispatcher("./AllDataServlet");
//				rd.forward(request,response);
			}
			else
			{
				out.print("<script>alert('Record Not Deleted....!');</script>");
//				rd = request.getRequestDispatcher("./Main.html");
//				rd.include(request, response);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
