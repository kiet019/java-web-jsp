<%-- 
    Document   : index
    Created on : Oct 4, 2022, 9:33:06 PM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="sample.dto.Plant" %>
<%@page import="sample.dao.PlantDAO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list;
                String[] tmp = {"out of stock", "available"};
                if(keyword == null && searchby == null) {
                    list = PlantDAO.getPlants("","");
                } else {
                    list = PlantDAO.getPlants(keyword, searchby);
                }
                String pid = request.getParameter("pid");
                if (pid != null) {
                    Plant p = PlantDAO.getPlant(Integer.parseInt(pid));
                    list = new ArrayList<>();
                    list.add(p);
                }
                if (list != null && !list.isEmpty()) {
                    out.println("<div class='sanpham'>");
                    for (Plant p : list) { %>
            <div>
                <table>
                    <tr><td><img src='<%= p.getImgpath()%>' class='plantimg'/></td></tr>    
                    <tr><td>Product ID:<%= p.getId() %></td></tr>
                    <tr><td>Product Name:<%= p.getName() %></td></tr>
                    <tr><td>Price:<%= p.getPrice() %></td></tr>
                    <tr><td>Status:<%= tmp[p.getStatus()] %></td></tr>
                    <tr><td>Category:<%= p.getCatename() %></td></tr>
                    <%
                        if (p.getStatus() != 0) {
                    %>
                    <tr><td><a href="mainController?action=addtocart&pid=<%= p.getId() %>">add to cart</a></td></tr>
                    <%
                        } else {
                    %>
                    <tr><td><a>comback soon</a><td></tr>
                    <%
                        }
                    %>
                </table>
            </div>
            <%
                    }
                    out.println("</div>");
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
