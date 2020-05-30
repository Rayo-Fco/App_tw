<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="Modelo.Mensaje"%>
<%
if(request.getSession().getAttribute("Usuario") == null)
                {
                    request.getRequestDispatcher("../index.jsp").forward(request, response);
                }
%>
<meta http-equiv="refresh" content="10; URL=Procesar_CargarMensaje.do" >
<table>
    <tr>
        <td> 
         <center>
                Mi Post 
        </center>
        </td>
        <td> 
         <center>
            <form action="Procesar_Mensaje.do" method="post" >
                <textarea rows="4" cols="50" name="txtMensaje">  </textarea> 
                <input type="submit" value="Publicar" />
            </form>
        </center>
        </td>
    </tr>
    <tr>
        <td colspan="2" ><center><font color="red"><b>${sessionScope.mensaje}</b></font></center></td>
    </tr>
    <tr>
        <td colspan="2">
            <table  >
                <tr style="height: 40px" >
                         <td colspan="3">------------------------------------------------------------------</td>
                         
                    </tr>
                <c:forEach var="f" items="${sessionScope.ListaMensaje}">
                    <tr>
                        <td style="width:35%">
                            Fecha:${f.getFechaMensaje()}
                        </td>
                        <td >
                            <c:if test ="${sessionScope.Usuario.id == f.idUsuario.id}">
                                <a href="Si/editarmensaje.jsp?Mensaje=${f.getMensaje()}&ID=${f.getId()}" target="_blank" onclick="window.open(this.href, this.target, 'width=450,height=100'); return false;">editar</a>
                            </c:if>
                                    
                        </td>
                        <td  style="width:35%">
                            Autor:${f.idUsuario.nick}
                        </td>
                    </tr>
                    <tr  style="height: 80px" >
                        <td colspan="3"> ${f.getMensaje()}</td>
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
                    <tr style="height: 20px" >
                        <td style="width: 20px">
                            
                        </td>
                         <td colspan="2">
                            <center>
                          <form action="Procesar_Comentario.do" method="post" >
                              <input type="hidden" name="idcomentario" value="${f.id}" />
                                <input  name="txtComentario" />  
                                <input type="submit" value="Comentar" />
                            </form>
                                </center>
                         </td>
                    </tr>
                     <tr style="height: 40px" >
                         <td colspan="3">------------------------------------------------------------------</td>
                         
                    </tr>
                </c:forEach>
                    
            </table>
        </td>
    </tr>
    
    
    
</table>
    