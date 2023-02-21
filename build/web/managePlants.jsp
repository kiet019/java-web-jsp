<%-- 
    Document   : managePlants
    Created on : Oct 20, 2022, 3:42:48 PM
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
            <section>
                <form action="mainController" method="post">
                    <input type="text" name="txtsearch" value="${param.txtsearch}">
                <input type="submit" value="searchPlant" name="action">
            </form>
        </section>
        <section>
            <c:if test="${requestScope.plantList.isEmpty()}">
                <h1>no plant founded</h1>
            </c:if>
            <table class="order">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>price</th>
                    <th>Img</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>CateID</th>
                    <th>action</th>
                </tr>
                <c:forEach var="plant" items="${requestScope.plantList}">
                    <tr>
                        <td>${plant.id}</td>
                        <td>${plant.name}</td>
                        <td>${plant.price}</td>
                        <td><img src=${plant.imgpath} class='plantimg'/></td>
                        <td>${plant.description}</td>
                        <td><c:choose>
                                <c:when test="${plant.status == 1}">available</c:when>
                                <c:otherwise>out of stock</c:otherwise>
                            </c:choose></td>
                        <td>${plant.cateid}</td>
                        <c:url var="myLink" value="mainController">
                            <c:param name="plantID" value="${plant.id}"></c:param>
                            <c:param name="status" value="${plant.status}"></c:param>
                            <c:param name="action" value="updateStatusPlant"></c:param>
                        </c:url>
                        <td><a href="${myLink}">block/unblock</a></td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
