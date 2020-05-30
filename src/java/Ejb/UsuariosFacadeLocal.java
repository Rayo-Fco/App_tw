/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Modelo.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Francisco
 */
@Local
public interface UsuariosFacadeLocal {

    void create(Usuarios usuarios);

    void edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    int count();
    
    public boolean ValidarUsuario (String nick,String clave);
    
    public boolean BuscarUsuario (String nick);
    
    public Usuarios VerUsuario(String nick,String clave);

    List<Usuarios> LikeUsuario(String nick);
    
    Usuarios VerUsuarioNick(String nick);
}
