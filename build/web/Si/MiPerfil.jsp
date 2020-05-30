<%-- 
    Document   : MiPerfil
    Created on : 12/07/2018, 01:01:31 PM
    Author     : Francisco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(request.getSession().getAttribute("Usuario") == null)
                {
                    response.sendRedirect("../");
                }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.Usuario.nombre}</title>
    
    </head>
    <body>
        <table style="width: 100%">
            <tr>
                <td style="width:70%; height:80px">
                <center>
                   <form action="Procesar_Busqueda.do" method="post" >
                                Buscar: <input type="text" name="txtBuscar" style="width: 70%;" />
                                <input type="submit" value="Buscar" />
                      </form>
                </center>
                </td>
                <td>
                    <form action="../" method="post">
                        <center><input type="submit" value="Volver Inicio" /></center>
                    </form>
                </td>
                <td>
                    <form action="../Procesar_CerrarSesion.do" method="post">
                        <center><input type="submit" value="Cerrar Sesion" /></center>
                    </form>
                </td>
            </tr>
        </table>
        <table border="2" style="width: 100%">
            <tr>
                <td style="text-align: center">
                    <h2>Mis Mensaje</h2>
                </td>
                <td style="text-align: center">
                     <h1> Mis Datos</h1>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                 <td style="text-align: center">
                     <h3>
                     Nick: ${sessionScope.Usuario.nick}<br>
                     Nombre: ${sessionScope.Usuario.nombre}<br>
                     Clave: ${sessionScope.Usuario.clave}<br>
                     Edad: ${sessionScope.Usuario.edad}<br>
                     </h3>
                </td>
            </tr>
        </table>
    </body>
</html>
