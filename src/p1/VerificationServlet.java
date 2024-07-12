package p1;

import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Properties;
import java.util.Random;


@WebServlet("/VerificationServlet")
public class VerificationServlet extends HttpServlet {
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		
		 HttpSession session = request.getSession();
		 
//	     String messageBody = generateProfileCardHTML();
		
//		String messageBody = "<html><body>"
//				+ "<h2><b>Thank you For Registration</b></h2>"
//			   + "<img src='https://visme.co/blog/wp-content/uploads/2020/02/header-1200.gif' alt='img' width='100%'/>"
//               + "<p><b>Here are your Registration Details :</b></p>"
//               + "<p><b>Your Username is : </b>" + session.getAttribute("Username") + "</p>"
//               + "<p><b>Your password is : </b>" + session.getAttribute("Password") + "</p>"
//               + "</body></html>";   
		 
		 String messageBody = "<html><body style='font-family: Arial, sans-serif;'>"
                 + "<div style='text-align: center;'>"
                 + "<h2 style='color: #4CAF50;'><b>Thank you for Registering with Us!</b></h2>"
                 + "<img src='https://visme.co/blog/wp-content/uploads/2020/02/header-1200.gif' alt='img' width='100%'/>"
                 + "<p style='font-size: 18px;'>We're thrilled to have you on board!</p>"
                 + "<div style='background-color: #f0f0f0; padding: 20px; border-radius: 10px; margin: 20px auto; max-width: 500px;'>"
                 + "<p style='font-size: 16px;'><b>Your Registration Details:</b></p>"
                 + "<p style='font-size: 16px;'><b>Username:</b> " + session.getAttribute("Username") + "</p>"
                 + "<p style='font-size: 16px;'><b>Password:</b> " + session.getAttribute("Password") + "</p>"
                 + "</div>"
                 + "<p style='font-size: 18px;'>We look forward to serving you!</p>"
                 + "</div>"
                 + "</body></html>";

        
        String sessionOtp = (String) session.getAttribute("otp");
        String sessionEmail = (String) session.getAttribute("Email");
        
        String userOtp = request.getParameter("digit1") + 
        					request.getParameter("digit2") + 
        					request.getParameter("digit3") + 
        					request.getParameter("digit4") + 
        					request.getParameter("digit5") + 
        					request.getParameter("digit6");
        
        if(sessionOtp != null && sessionOtp.equals(userOtp)) 
        {
            out.println("<h1>OTP is Correct so Registration is Successfull</h1>");
            sendEmail(sessionEmail,"Successfully Registration" ,messageBody);
        } else 
        {
            out.println("<h1>Incorrect OTP. Registration Failed.</h1>");
        }
    }

	public void sendEmail(String recipientEmail, String subject, String messageBody) {
	    // Sender's email ID and password need to be mentioned
	    final String username = "shivlab2023@gmail.com"; // <-- Replace with your Gmail username
	    final String password = "poxi dwze fbqz edzm";

	    // Setting up mail server properties
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    // Creating a new session with an authenticator
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	        // Creating a default MimeMessage object
	        Message message = new MimeMessage(session);

	        // Setting From: header field of the header
	        message.setFrom(new InternetAddress(username));

	        // Setting To: header field of the header
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Specified recipient email address

	        // Setting Subject: header field
	        message.setSubject(subject);

	        // Now set the actual message
	        message.setContent(messageBody, "text/html"); // Set content as HTML

	        // Sending the message
	        Transport.send(message);

	        // Writing response
	        System.out.println("Sent message successfully to " + recipientEmail);
	    } catch (MessagingException e) {
	        // Print the stack trace to the console
	        e.printStackTrace();

	        // Write the error message to the response
	        System.out.println("Failed to send the email. Error: " + e.getMessage());
	    }
	}

}
