<form action="../Procesar_EditarComentario.do" method="post" >
                <input type="hidden" name="txtID" value="<%=request.getParameter("ID") %>" />
                <textarea rows="2" cols="50" name="txtComentario"><%=request.getParameter("Comentario") %></textarea> 
                <input type="submit" value="Editar" />
</form>
