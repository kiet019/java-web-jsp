<%-- 
    Document   : profile
    Created on : Oct 5, 2022, 8:41:01 PM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.AccountDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="myCss.css" type="text/css"/>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            if (name == null) {
        %>
        <p><font color="red">you must login to view profile</p>
        <a href="login.jsp">login</a>
        <% 
            } else {     
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Profile <%=name%></h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <a href="mainController?action=personal">View all orders</a>
        </section>
        <section>
            <%
                Account acc = (Account) session.getAttribute("acc");
            %>
            <table width=30%>
                <tr><td>Email</td><td><%= acc.getEmail()%></td></tr>
                <tr><td>Name</td><td><%= acc.getFullname()%></td></tr>
                <tr><td>Phone</td><td><%= acc.getPhone()%></td></tr>
            </table>
        </section>
        <section>
            <h3>Update infomation</h3>
            <form action="mainController" method="post">
                <table width=30%>
                    <tr><td>Input new name: </td><td><input type="text" name="newName"></td></tr>
                    <tr><td>Input new phone: </td><td><input type="text" name="newPhone"></td></tr>
                </table>
                <input type="submit" name="action" value="changeInfo">
            </form>
            <%   String result = (String) request.getAttribute("result");
                if (result != null) {
                    if (result.equals("true")) {%>
                        <p>Update success, please login again</p>
                    <%} else {%>
                        <p>new name or new phone is empty</p>
                    <%}%>
              <%}%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%}%>
    </body>
</html>