
<table style="width: 100%; height: 20%;" border="2" >
	<tbody>
		<tr>
                    <td style="width: 70%;">
                        <center>
                            <form action="Procesar_Busqueda.do" method="post" >
                                Buscar: <input type="text" name="txtBusqueda" style="width: 70%;" />
                                <input type="submit" value="Buscar" /><br>
                                <center><font color="red">${sessionScope.busqueda}</font></center>
                            </form>
                        </center>
                        </td>
                        
                       
			<td style="width: 30%;">
                          <center>   
                            <% 
                            if(request.getSession().getAttribute("Usuario") == null)
                            {
                            %>
                            <%@include file="No/login.jsp" %>
                           <% 
                            }
                            else
                            {
                            %>
                             <%@include file="Si/perfil.jsp" %>
                           <% } %>
                            </center>
                        </td>
		</tr>
	</tbody>
</table>


