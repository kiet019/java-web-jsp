<%-- 
    Document   : header_loginedUser
    Created on : Oct 4, 2022, 10:34:33 PM
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
            <nav>
                <ul>
                    <li><a href="mainController?action=">Home</a></li>
                    <li><a href="mainController?action=changeprofile">change profile</a></li>
                    <li><a href="mainController?action=personal&view=viewComplete">completed orders</a></li>
                    <li><a href="mainController?action=personal&view=viewCanceled">canceled orders</a></li>
                    <li><a href="mainController?action=personal&view=viewProcessing">processing orders</a></li>
                    <li><form action="mainController" method="post">
                            <input type="date" name="from" placeholder="from">
                            <input type="date" name="to" placeholder="to">
                        <input type="submit" value="searchDate" name="action">
                        </form>
                    </li>
                </ul> 
            </nav>
        </header>
    </body>
</html>
