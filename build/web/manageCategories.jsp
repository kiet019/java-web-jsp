<%-- 
    Document   : manageCategories
    Created on : Oct 20, 2022, 5:01:59 PM
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
                <input type="submit" value="searchCategory" name="action">
            </form>
        </section>
        <section>
            <c:if test="${requestScope.accountList.isEmpty()}">
                <h1>no category founded</h1>
            </c:if>
            <table class="order">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                </tr>
                <c:forEach var="category" items="${requestScope.cateList}">
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
