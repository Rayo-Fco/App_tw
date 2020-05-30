<%-- 
    Document   : busqueda
    Created on : 12/07/2018, 03:55:51 PM
    Author     : Francisco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TIWEEKER</title>
        <%@include file="header.jsp" %>
    </head>
    <body>
        <table style="width: 100%" >
            <tr>
                <td>
                    <h2> Perfiles</h2>
                </td>
                <td>
                    <h2> Mensaje</h2>
                </td>
                <td>
                    <%if(request.getSession().getAttribute("Usuario") != null){ %>       
                    <h2> Hashtag</h2>
                    <%} %>       
                </td>
            </tr>
            <tr>
                <td style="width:30%; vertical-align: top" >
                    <table style="width: 100%" >
                        <tr>
                            <td>---------------------------------------</td>
                        </tr>
                        <c:forEach var="f" items="${sessionScope.ListaPerfilesBusqueda}" >
                            
                            <tr>
                                 <td>
                                     <a href="Perfil.do?Perfil=${f.nick}" >${f.nick}</a>
                                </td>
                            </tr>
                            <tr>
                               <td>---------------------------------------</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td style="vertical-align: top" >
                    <table>
                         <tr>
                            <td  colspan="2">---------------------------------------</td>
                        </tr>
                        <c:forEach var="p" items="${sessionScope.ListaMensajeBusqueda}" >
                            <tr>
                                <td >
                                    ${p.mensaje}
                                </td>
                                <td >
                                    Autor: ${p.idUsuario.nick}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">---------------------------------------</td>
                            </tr>
                        </c:forEach>
                   </table>
                </td>
                <td style="vertical-align: top">
                    <%if(request.getSession().getAttribute("Usuario") != null){ %>       
                     <table>
                         <tr>
                            <td  colspan="2">---------------------------------------</td>
                        </tr>
                        <c:forEach var="u" items="${sessionScope.ListaHashtagBusqueda}" >
                            <tr>
                                <td >
                                    ${u.mensaje}
                                </td>
                                <td >
                                    Autor: ${u.idUsuario.nick}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">---------------------------------------</td>
                            </tr>
                        </c:forEach>
                   </table>
                    <%} %>       
                </td>
            </tr>
            
        </table>
    </body>
</html>
