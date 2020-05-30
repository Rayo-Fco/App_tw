<%
if(request.getSession().getAttribute("Usuario") != null)
                {
                    response.sendRedirect("/");
                }
%>
<%-- 
    Document   : registrar
    Created on : 6/07/2018, 11:17:02 AM
    Author     : Francisco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido a Tiweeker</h1>
        <form action="Procesar_Registro.do" method="post">
            <input type="text" name="txtNick" value="${sessionScope.campo.getNick()}"/> Nick <br />
            <input type="text" name="txtNombre" value="${sessionScope.campo.getNombre()}"/> Nombre <br />
            <input type="text" name="txtEdad" value="${sessionScope.campo.getEdad()}"/> Edad <br />
            <input type="password" name="txtPass" value="${sessionScope.campo.getClave()}" /> Password <br />
            <font color="red">${sessionScope.registro}</font><br/>
            
            <input type="submit" value="Registrar..." />
        </form>
    </body>
</html>
