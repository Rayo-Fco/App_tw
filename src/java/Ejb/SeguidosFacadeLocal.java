/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Modelo.Seguidos;
import Modelo.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Francisco
 */
@Local
public interface SeguidosFacadeLocal {

    void create(Seguidos seguidos);

    void edit(Seguidos seguidos);

    void remove(Seguidos seguidos);

    Seguidos find(Object id);

    List<Seguidos> findAll();

    List<Seguidos> findRange(int[] range);

    int count();
    
    List<Seguidos> BuscarSeguidos(Usuarios u);
    
    List<Seguidos> BuscarSeguidores(Usuarios u);
    
    Seguidos BuscarIDSeguido(Usuarios usuario,Usuarios seguidor);
    
}
