package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllDataServlet")
public class AllDataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        String searchQuery = request.getParameter("search"); // Get search query from request parameter

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "shiv");
            Statement st = con1.createStatement();
            
            String sqlQuery = "SELECT * FROM student";

            if (searchQuery != null && !searchQuery.isEmpty()) {
                sqlQuery += " WHERE name LIKE '%" + searchQuery + "%'";
            }

            ResultSet rs = st.executeQuery(sqlQuery);

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Data</title>");
            out.println("<style>");
            out.println("table {");
            out.println("    width: 100%;");
            out.println("    border-collapse: collapse;");
            out.println("}");
            out.println("th, td {");
            out.println("    padding: 15px;");
            out.println("    text-align: left;");
            out.println("}");
            out.println("th {");
            out.println("    background-color: #f2f2f2;");
            out.println("}");
            out.println("tr:nth-child(odd) {");
            out.println("    background-color: #f2f2f2;");
            out.println("}");
            out.println("tr:hover {");
            out.println("    background-color: #ddd;");
            out.println("}");
            out.println(".button {");
            out.println("    background-color: red;");
            out.println("    border: none;");
            out.println("    color: white;");
            out.println("    padding: 10px 20px;");
            out.println("    text-align: center;");
            out.println("    text-decoration: none;");
            out.println("    display: inline-block;");
            out.println("    font-size: 16px;");
            out.println("    cursor: pointer;");
            out.println("}");
            out.println(".search-form {");
            out.println("    margin-bottom: 20px;");
            out.println("    margin-top: 20px;");
            out.println("}");
            out.println(".search-input {");
            out.println("    padding: 10px;");
            out.println("    font-size: 16px;");
            out.println("}");
            out.println(".search-button {");
            out.println("    padding: 10px 20px;");
            out.println("    font-size: 16px;");
            out.println("    cursor: pointer;");
            out.println("    color: white;");
            out.println("    border: none;");
            out.println("}");
            out.println(".button2 {");
            out.println("    font-size: 26px;");
            out.println("    cursor: pointer;");
            out.println("    color: blue;");
            out.println("    border: none;");
            out.println("    background-color: #fafafa;");

            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
//            <=========  Search =============>
            out.println("<form method='post' class='search-form'>");
            out.println("<input type='text' name='search' placeholder='Enter name to search' class='search-input'>");
            out.println("<button type='submit' class='search-button'>&#128269</button>");
            out.println("<button type='button' class='button2' onclick='window.location.href=window.location.href'>&#8635</button>");
            out.println("</form>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Roll No</th>");
            out.println("<th>Name</th>");
            out.println("<th>City</th>");
            out.println("<th>Marks</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");
                out.println("<td>" + rs.getInt(4) + "</td>");
                // Add update and delete buttons
                out.println("<td><form action='DeleteServlet' method='post'><input type='hidden' name='sroll' value='" + rs.getInt(1) + "'><input type='submit' value='&#128465' class='button'></form></td>");
                out.println("</tr>");
            }

//            while (rs.next()) {
//                out.println("<tr>");
//                out.println("<td>" + rs.getInt(1) + "</td>");
//                out.println("<td>" + rs.getString(2) + "</td>");
//                out.println("<td>" + rs.getString(3) + "</td>");
//                out.println("<td>" + rs.getInt(4) + "</td>");
//                out.println("</tr>");
//            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
