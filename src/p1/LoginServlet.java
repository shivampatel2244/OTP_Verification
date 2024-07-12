package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "shiv");
            
            String Username = request.getParameter("uname").trim();
    		String Password = request.getParameter("upassword").trim();
            
//    		Statement st = con.createStatement();
    		PreparedStatement ps = con.prepareStatement("select * from UserRegistration where username='"+Username+"' and password='"+Password+"'");
//            
//          ps.setString(1, Username);
//          ps.setString(2, Password);
    		
//    		String query = "select * from UserRegistration where username='"+Username+"' and password='"+Password+"'";
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) 
            {
            	out.print("<script>localStorage.setItem('UserName', '" + Username+ "');</script>");
            	out.print("<script>alert('Login Successfully');</script>");
//            	HttpSession session = request.getSession(true);
            	
//            	Cookie ck = new Cookie("Username", Username);
//            	response.addCookie(ck);
                 	
//            	session.setAttribute("Username", Username);            	
//            	response.sendRedirect(request.getContextPath() + "/SessioData");
            	
//            	response.sendRedirect("./Main.html");
//            	
            	rd = request.getRequestDispatcher("./Main.html");
            	rd.include(request, response);
			}
            else
            {
            	out.print("<script>alert('Please Enter Valid Username and Password');</script>");
            	rd = request.getRequestDispatcher("./LoginRegistration.html");
            	rd.include(request, response);
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
