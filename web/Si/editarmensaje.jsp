<form action="../Procesar_EditarMensaje.do" method="post" >
                <input type="hidden" name="txtID" value="<%=request.getParameter("ID") %>" />
                <textarea rows="4" cols="50" name="txtMensaje"><%=request.getParameter("Mensaje") %></textarea> 
                <input type="submit" value="Editar" />
</form>