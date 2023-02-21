<%-- 
    Document   : registration
    Created on : Oct 4, 2022, 9:40:36 PM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="myCss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <form action="mainController" method="post" class="formregister">
                <h1>Register</h1>
                <table>
                    <tr><td>email</td><td><input type="text" name="txtemail"></td></tr>
                    <tr><td>fullname</td><td><input type="text" name="txtfullname"></td></tr>
                    <tr><td>password</td><td><input type="password" name="txtpassword"></td></tr>
                    <tr><td>phone</td><td><input type="text" name="txtphone"></td></tr>
                    <tr><td colspan="2"><input type="submit" value="register" name="action"></td></tr>
                </table>
            </form>
        </section>
        <section>
            <%
                ArrayList<String> err = (ArrayList<String>) request.getAttribute("err");
                if (err != null) {
                    for (String error : err) {
                    
            %>  
                <p color="red"><%= error %></p>
            <%
                    }
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
