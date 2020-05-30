/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Modelo.Comentario;
import Modelo.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Francisco
 */
@Local
public interface ComentarioFacadeLocal {

    void create(Comentario comentario);

    void edit(Comentario comentario);

    void remove(Comentario comentario);

    Comentario find(Object id);

    List<Comentario> findAll();

    List<Comentario> findRange(int[] range);

    int count();
    
    boolean ValidarComentario(int id,Usuarios u);
    
    Comentario BuscarUnoComentario(int u);
    
    
}
