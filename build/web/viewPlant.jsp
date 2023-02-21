<%-- 
    Document   : viewPlant
    Created on : Oct 17, 2022, 9:00:15 PM
    Author     : kietl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>this is view plant</h1>
        <jsp:useBean id="plantObj" class="sample.dto.Plant" scope="request"/>
            <table>
                <tr><td><img src='${plantObj.imgpath}' class='plantimg'/></td></tr>    
                <tr><td>Product ID:${plantObj.id}</td></tr>
                <tr><td>Product Name:${plantObj.name}</td></tr>
                <tr><td>Price:${plantObj.price}</td></tr>
                <tr><td>Status:${plantObj.status}</td></tr>
                <tr><td>Category:${plantObj.catename}</td></tr>
                <tr><td><a href="mainController?action=addtocart&pid=${plantObj.id}">add to cart</a></td></tr>
            </table>
        </body>
    </body>
</html>
