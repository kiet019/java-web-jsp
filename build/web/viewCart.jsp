<%-- 
    Document   : viewCart
    Created on : Oct 7, 2022, 4:04:06 PM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="sample.dto.Plant" %>
<%@page import="sample.dao.PlantDAO" %>
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
            <%
                String name = (String) session.getAttribute("name");
                if (name != null) {
            %>
            <h3>Welcome <%= name%> comback</h3>
            <h3><a href="mainController?action=logout">logout</a></h3>
            <%    
                }
            %>
            <font style='color:red;'><%=((String) request.getAttribute("WARNING") == null)?"":(String) request.getAttribute("WARNING")%></font>
            <table width="100%" class="viewcart">
                <tr><td>Product id</td><td>image</td><td>quantity</td><td>price</td><td>action</td></tr>
                <%
                    HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                    int money = 0;
                    if (cart != null) {
                        Set<String> pids = cart.keySet();
                        for (String pid : pids){
                            int quantity = cart.get(pid);
                            Plant p = PlantDAO.getPlant(Integer.parseInt(pid));
                            money = money + quantity*p.getPrice();
                            
                %>
                <tr>
                <form action="mainController" method="post">
                    <td><input type="hidden" name="pid" value="<%= pid%>"><a href="mainController?pid=<%= pid%>"><%= pid%></a></td>
                    <td><img src='<%= p.getImgpath()%>' class="paintimg"/></td>
                    <td><input type="number" value="<%= quantity%>" name="quantity"></td>
                    <td><p><%= p.getPrice()%></p></td>
                    <td><input type="submit" value="update" name="action">
                        <input type="submit" value="delete" name="action"></td>
                    <td><a href="getPlantServlet?pid=<%= pid%>"><%= pid%></a></td>
                </form>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td>Your cart is empty</td></tr>
                <%
                    }
                %>
                <tr><td>Total money: <%= money%></td></tr>
            </table>
        </section>
        <section>
            <form action="mainController" method="post">
                <input type="submit" name="action" value="saveOrder" class="submitorder">
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
