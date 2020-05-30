<%
if(request.getSession().getAttribute("Usuario") == null)
                {
                    request.getRequestDispatcher("../index.jsp").forward(request, response);
                }
%>
<body onLoad="setTimeout('window.close()',1000)">
    <h1 >
        ${sessionScope.respuestaeditar}
    </h1>
</body>
