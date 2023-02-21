<%-- 
    Document   : personalPage
    Created on : Oct 4, 2022, 10:41:23 PM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="sample.dto.Order" %>
<%@page import="sample.dao.OrderDAO" %>
<%@page import="sample.dao.AccountDAO" %>
<%@page import="sample.dto.Account" %>
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
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie aCookie : c) {
                    if (aCookie.getName().equals("selector")){
                        token = aCookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null){
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                login = true;
            }
            if (!login) {
        %>
        <p><font color="red">you must login to view personal page</p>
        <a href="login.jsp">login</a>
        <% 
            } else {     
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <h2><%=error%></h2>
            <%
                }
            %>
        </header>
        <section>
            <h3>Welcome <%=name%> comback</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <a href="mainController?action=personal">View all orders</a>
        </section>
        <section>
            <%
                ArrayList<Order> list = (ArrayList<Order>) request.getAttribute("list2");
                if (list == null) {
                    list = OrderDAO.getOrders(email);
                } 
                String[] status = {"", "processing", "completed", "canceled"};
                String view = request.getParameter("view");
                if (list != null && !list.isEmpty()) {
                    if (view == null) {
                        for (Order ord: list) {%>
                            <table class="order">
                                <tr><td>OrderID</td><td>Order Date</td><td>Ship Date</td><td>Order Status</td><td>Action</td></tr>
                                <tr><td><%= ord.getOrderID()%></td>
                                    <td><%= ord.getOrderDate()%></td>
                                    <td><%= ord.getShipDate()%></td>
                                    <td><%= status[ord.getStatus()]%>
                                        <br><%if(ord.getStatus()==1)%><a href="#"> cancel order</a>
                                    </td>
                                    <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td>
                                </tr>
                            </table>
                            <%}//end for
                    }else {
                        if(view.equals("viewComplete")) {
                            for (Order ord: list) {
                                if (ord.getStatus() == 2){%>
                                    <table class="order">
                                        <tr><td>OrderID</td><td>Order Date</td><td>Ship Date</td><td>Order Status</td><td>Action</td></tr>
                                        <tr><td><%= ord.getOrderID()%></td>
                                            <td><%= ord.getOrderDate()%></td>
                                            <td><%= ord.getShipDate()%></td>
                                            <td><%= status[ord.getStatus()]%></td>
                                            <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td>
                                        </tr>
                                    </table>
                                <%}
                            }//end for
                        } else if (view.equals("viewCanceled")){
                            for (Order ord: list) { 
                                if (ord.getStatus() == 3){%>
                                    <table class="order">
                                        <tr><td>OrderID</td><td>Order Date</td><td>Ship Date</td><td>Order Status</td><td>Action</td></tr>
                                        <tr><td><%= ord.getOrderID()%></td>
                                            <td><%= ord.getOrderDate()%></td>
                                            <td><%= ord.getShipDate()%></td>
                                            <td><%= status[ord.getStatus()]%>
                                            <br>
                                            <a href="mainController?action=orderagain&orderID=<%=ord.getOrderID()%>">order again</a>
                                            </td>
                                            <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td>
                                        </tr>
                                    </table>
                                <%}
                            }//end for
                        } else if (view.equals("viewProcessing")) {
                            for (Order ord: list) { 
                                if (ord.getStatus() == 1){%>
                                    <table class="order">
                                        <tr><td>OrderID</td><td>Order Date</td><td>Ship Date</td><td>Order Status</td><td>Action</td></tr>
                                        <tr><td><%= ord.getOrderID()%></td>
                                            <td><%= ord.getOrderDate()%></td>
                                            <td><%= ord.getShipDate()%></td>
                                            <td><%= status[ord.getStatus()]%>
                                                <br><%if(ord.getStatus()==1)%><a href="mainController?action=cancelorder&orderID=<%= ord.getOrderID() %>"> cancel order</a>
                                            </td>
                                            <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td>
                                        </tr>
                                    </table>
                                <%}
                            }//end for
                        }
                    }
                } else {%>
            <p>You don't have any order</p>
            <%}%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%}%>
    </body>
</html>
