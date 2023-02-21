<%-- 
    Document   : manageOrders
    Created on : Oct 20, 2022, 1:29:43 PM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="myCss.css" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.name == null}">
            <c:redirect url="mainController"></c:redirect>
        </c:if>
        <c:import url="header_loginedAdmin.jsp"></c:import>
            <form action="mainController" method="post">
                <input type="text" name="txtsearch" value="${param.txtsearch}">
            <input type="submit" value="searchOrder" name="action">
        </form>
        <c:if test="${requestScope.orderList.isEmpty()}">
            <h1>no order founded</h1>
        </c:if>
        <table class="order">
            <tr>
                <th>OrderID</th>
                <th>OrderDate</th>
                <th>ShipDate</th>
                <th>Status</th>
                <th>AccountID</th>
            </tr>
            <c:forEach var="order" items="${requestScope.orderList}">
                <tr>
                    <td>${order.orderID}</td>
                    <td>${order.orderDate}</td>
                    <td><c:choose>
                            <c:when test="${order.status == 1}">
                                <form action="mainController" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderID}">
                                    <input type="date" name="shipdate">
                                    <input type="submit" name="action" value="updateShipDate">
                                </form>
                            </c:when>
                            <c:otherwise>${order.shipDate}</c:otherwise>
                        </c:choose></td>
                    <td><c:choose>
                            <c:when test="${order.status == 1}">processing</c:when>
                            <c:when test="${order.status == 2}">completed</c:when>
                            <c:when test="${order.status == 3}">cancelled</c:when>
                    </c:choose></td>
                    <td>${order.accID}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
