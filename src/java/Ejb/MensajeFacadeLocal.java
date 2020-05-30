/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Modelo.Mensaje;
import Modelo.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Francisco
 */
@Local
public interface MensajeFacadeLocal {

    void create(Mensaje mensaje);

    void edit(Mensaje mensaje);

    void remove(Mensaje mensaje);

    Mensaje find(Object id);

    List<Mensaje> findAll();

    List<Mensaje> findRange(int[] range);

    int count();

    public List<Mensaje> BuscarMensaje(Usuarios u);

    public boolean ValidarMensaje(int id, Usuarios u);

    public boolean EditarMensaje(int id, String mensaje);
    
    public List<Mensaje> BuscarMensajeTodos(int u);
    
    public Mensaje BuscarUnoMensaje(int u);
    
    public List<Mensaje> LikeMensaje(String mensaje);
    
    public List<Mensaje> LikeHashtag(String mensaje);
}
