# OTP_Verification

Overview
This project is an OTP (One-Time Password) Verification System implemented using Java Servlets and HTML. It includes email-based OTP verification for enhanced security. The application verifies a user's identity by sending an OTP to the user's email and validating the input provided by the user against the generated OTP.

Features :
Email OTP Generation: Generates a unique OTP and sends it to the user's email address.
OTP Validation: Validates the user input against the generated OTP.
Success and Failure Handling: Provides feedback to the user about the success or failure of the OTP verification.
Send Credentials: Sends user credentials to the user's email upon successful OTP verification.
User-Friendly Interface: Simple HTML page for user interaction.

Technology Stack
Backend: Java Servlets
Frontend: HTML, CSS
Email Service: JavaMail API (or any other email service library you used)
Web Server: Apache Tomcat (or specify the server you are using)

Usage
Request OTP: Enter your email address on the provided HTML form and request an OTP.
Check Email: Check your email inbox for the OTP sent by the system.
Verify OTP: Enter the received OTP in the verification form and submit.
Receive Credentials: If the OTP is correct, you will receive an email with your credentials.
Success/Failure Notification: The system will notify you if the OTP is correct or incorrect.
