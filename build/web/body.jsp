<table style="width: 100%;" border="0" >
        <tr>
            <td style="width: 30%;">
              <center>   
                <% 
                if(request.getSession().getAttribute("Usuario") == null)
                {
                %>
                <%@include file="No/home.jsp" %>
               <% 
                }
                else
                {
                %>
                 <%@include file="Si/mensaje.jsp" %>
               <% } %>
                </center>
            </td>
        </tr>
</table>
