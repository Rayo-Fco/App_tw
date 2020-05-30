<%
if(request.getSession().getAttribute("Usuario") == null)
                {
                    request.getRequestDispatcher("../index.jsp").forward(request, response);
                }
%>

<table border="2" cellpadding="1" style="width:100%">
<tr>
    <td colspan="2" >
        <form action="Procesar_CerrarSesion.do" method="post">
            <center><input type="submit" value="Cerrar Sesion" /></center>
        </form>
    </td>
</tr>
<tr>
    <td colspan="2" ><center><b><a href="Si/MiPerfil.jsp">${sessionScope.Usuario.nombre}</a></b></center></td>
</tr>
<tr>
    <td colspan="2"><center>Tweet:${sessionScope.Total}</center></td>
</tr>
<tr>
<td><center>Seguidos</center></td>
<td><center>Seguidores</center></td>
</tr>
<tr>
<td><center>${sessionScope.Seguidos}</center></td>
<td><center>${sessionScope.Seguidores}</center></td>
</tr>

</table>