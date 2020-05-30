<%-- 
    Document   : busqueda
    Created on : 12/07/2018, 03:55:51 PM
    Author     : Francisco
--%>
<%
if(request.getSession().getAttribute("BusquedaPerfilMensaje") == null)
                {
                    response.sendRedirect("../");
                }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TIWEEKER</title>
        <%@include file="../header.jsp" %>
    </head>
    <body>
        <table style="width: 100%" >
            <tr>
                <td>
                    <h2> Mensaje</h2>
                </td>
                <td>
                    <h2> Informacion</h2>
                </td>
            </tr>
            <tr>
                <td style="width:30%; vertical-align: top" >
                    <table style="width: 100%" >
                        <tr>
                            <td colspan="3">---------------------------------------</td>
                        </tr>
                        <c:forEach var="f" items="${sessionScope.BusquedaPerfilMensaje}" >
                            
                            <tr>
                                <td>
                                    <b>Autor:</b>${f.idUsuario.nick}
                                </td>
                                <td >
                                    
                                </td>
                                <td>
                                    <b>Fecha:</b>${f.getFechaMensaje()}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><b>Mensaje: </b>${f.mensaje}</td>
                            </tr>
                            <c:forEach var="p" items="${f.getComentarioList()}">
                                    <tr>
                                        <td style="text-align: right">
                                       Fecha:
                                    </td>
                                    <td>
                                         ${p.fechaHora}
                                    </td>
                                    <td>
                                        Autor:${p.idUsuario.nick}
                                         <c:if test ="${sessionScope.Usuario.id == p.idUsuario.id}">
                                            <a href="Si/editarcomentario.jsp?Comentario=${p.comentario}&ID=${p.getId()}" target="_blank" onclick="window.open(this.href, this.target, 'width=450,height=100'); return false;">editar</a>
                                        </c:if>
                                    </td>
                                    </tr>
                                    <tr  style="height: 30px" >
                                        <td></td>
                                        <td colspan="2">${p.comentario}</td>
                                    </tr>

                                </c:forEach>
                            <tr>
                                <td colspan="3">---------------------------------------</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td style="vertical-align: top" >
                    <table>
                         <tr>
                            <td  >---------------------------------------</td>
                        </tr>
                              <tr>
                                <td >
                                   <h2> Edad: ${sessionScope.BusquedaPerfil.edad}</h2>
                                </td>
                                 </tr>
                                  <tr>
                                <td >
                                   <h2> Nick: ${sessionScope.BusquedaPerfil.nick}</h2>
                                </td>
                                 </tr>
                                <tr>
                                <td >
                                    <h2>Nombre: ${sessionScope.BusquedaPerfil.nombre}</h2>
                                </td>
                                 </tr>
                                 
                            <tr>
                                <td >---------------------------------------</td>
                            </tr>
                   </table>
                </td>
                <td style="vertical-align: top">
                   <%
                        if(request.getSession().getAttribute("Usuario") != null)
                                {
                        %>
                    <table>
                         <% int o = 0;%>
                        <c:forEach var="p" items="${sessionScope.Seguidos2}">
                            
                            <c:if test ="${sessionScope.BusquedaPerfil != p.idSeguidor}">
                                <%  o = 2;%>
                            </c:if>
                            <c:if test ="${sessionScope.BusquedaPerfil == p.idSeguidor}">
                                <%  o = 3;%>
                            </c:if>
                        </c:forEach>
                         <%  if(o == 2 || o == 0){%>
                            <tr>
                                 <a href="Seguir.do?Nick=${sessionScope.BusquedaPerfil.nick}"> Seguir</a>
                            </tr>
                            <%} 
                            if(o== 3)
                              {
                            %>
                            <tr>
                                 <a href="DejardeSeguir.do?Nick=${sessionScope.BusquedaPerfil.nick}"> Dejar de Seguir</a>
                            </tr>
                            <%} %>
                         <%
                             }
                        %>
                    </table>
                </td>
            </tr>
            
        </table>
    </body>
</html>