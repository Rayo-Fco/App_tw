<form action="Procesar_Login.do" method="post">
    <table  style="width:100%">
    <tr>
        <td colspan="2" > <center>Inicio de Sesion </center> </td>
    </tr>
    <tr>
        <td align="right"> Nick:</td>
        <td align="left"><input type="text" name="txtNick" style="width:80%" /></td>
    </tr>
    <tr>
        <td align="right"> Password:</td>
        <td align="left"> <input type="password" name="txtPass" style="width:80%"/></td>
    </tr>
    <tr>
         <td colspan="2" > <center><font color="red">${sessionScope.registro}</font></center></td>
    </tr>
    <tr>
        <td colspan="2" > <center><input type="submit" value="INICIAR" /></center></td>
    </tr>
    </table>
    
    
</form>
