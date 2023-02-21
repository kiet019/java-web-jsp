<%-- 
    Document   : OrderDetail.jsp
    Created on : Oct 5, 2022, 2:49:33 PM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="sample.dto.OrderDetail" %>
<%@page import="sample.dao.OrderDAO" %>
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
        <p><font color="red">you must login to view your order</p>
        <% 
            } else {     
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <a href="personalPage.jsp">View all orders</a>
        </section>
        <section>
            <%  String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list = OrderDAO.getOrderDetail(orderID);
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetail detail : list) {%>
                        <table class="order">
                            <tr><td>Order ID</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>Quantity</td></tr>
                            <tr><td><%= detail.getOrderID()%></td><td><%= detail.getPlantID()%></td>
                                <td><%= detail.getPlantName()%></td>
                                <td><img src='<%= detail.getImgPath()%>' class='paintimg'/><br/><%= detail.getPrice()%></td>
                                <td><%= detail.getQuantity()%></td>
                                <% money = money + detail.getPrice() * detail.getQuantity();%>
                            </tr>
                        </table>    
                      <%}%>
                        <h3> Total money: <%= money %></h3>
                  <%} else {
                        %>
                        <p>You don't have any order</p>
                  <%}
                }%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%}
        %>
</html>