/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Modelo.Comentario;
import Modelo.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Francisco
 */
@Stateless
public class ComentarioFacade extends AbstractFacade<Comentario> implements ComentarioFacadeLocal {

    @PersistenceContext(unitName = "App_twPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioFacade() {
        super(Comentario.class);
    }
    
    @Override
    public boolean ValidarComentario(int id,Usuarios u)
    {
    Query query = em.createQuery("SELECT a FROM Comentario a WHERE a.idUsuario = :idUsuario AND a.id = :id");
        query.setParameter("idUsuario", u);
        query.setParameter("id", id);
        if (query.getResultList().size() > 0) {
            return true;
        }
        return false;
    }
    
    @Override
     public Comentario BuscarUnoComentario(int u)
    { 
        Query query = em.createQuery("SELECT a FROM Comentario a WHERE a.id = :mensaje");
        query.setParameter("mensaje", u);
        Comentario list = (Comentario)query.getSingleResult();
        return list;
    }
    
}
