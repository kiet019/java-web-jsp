<%-- 
    Document   : hearder_loginedAdmin
    Created on : Oct 19, 2022, 11:16:26 AM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="myCss.css" type="text/css"/>
    </head>
    <body>
    <header>
        <ul>
            <li><a href="mainController?action=manageAccounts">Manage Account</a></li>
            <li><a href="mainController?action=manageOrders">Manage Order</a></li>
            <li><a href="mainController?action=managePlants">Manage Plants</a></li>
            <li><a href="mainController?action=manageCategories">Manage Category</a></li>
            <li>Welcome ${sessionScope.name} | <a href="mainController?action=logout">logout</a></li>
        </ul>
    </header>
</body>
</html>
