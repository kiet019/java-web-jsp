<%-- 
    Document   : AdminIndex
    Created on : Oct 19, 2022, 11:13:45 AM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        
    </body>
</html>
