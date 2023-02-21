<%-- 
    Document   : manageAccounts
    Created on : Oct 19, 2022, 12:08:09 PM
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
                <input type="submit" value="searchAccount" name="action">
            </form>
        </section>
        <section>
            <c:if test="${requestScope.accountList.isEmpty()}">
                <h1>no account founded</h1>
            </c:if>
            <table class="order">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Full name</th>
                    <th>status</th>
                    <th>phone</th>
                    <th>role</th>
                    <th>action</th>
                </tr>
                <c:forEach var="acc" items="${requestScope.accountList}">
                    <tr>
                        <td>${acc.accid}</td>
                        <td>${acc.email}</td>
                        <td>${acc.fullname}</td>
                        <td><c:choose>
                                <c:when test="${acc.status == 1}">active</c:when>
                                <c:otherwise>inactive</c:otherwise>
                            </c:choose></td>
                        <td>${acc.phone}</td>
                        <td><c:choose>
                                <c:when test="${acc.role == 1}">admin</c:when>
                                <c:otherwise>user</c:otherwise>
                            </c:choose></td>
                        <td>
                            <c:if test="${acc.role == 0}">
                                <c:url var="myLink" value="mainController">
                                    <c:param name="email" value="${acc.email}"></c:param>
                                    <c:param name="status" value="${acc.status}"></c:param>
                                    <c:param name="action" value="updateStatusAccount"></c:param>
                                </c:url>
                                <a href="${myLink}">block/unblock</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
