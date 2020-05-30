/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Modelo.Mensaje;
import Modelo.Seguidos;
import Modelo.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Francisco
 */
@Stateless
public class MensajeFacade extends AbstractFacade<Mensaje> implements MensajeFacadeLocal {

    @PersistenceContext(unitName = "App_twPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeFacade() {
        super(Mensaje.class);
    }
    
    @Override
    public List<Mensaje> BuscarMensaje(Usuarios u)
    { 
        Query query = em.createQuery("SELECT a FROM Mensaje a WHERE a.idUsuario = :idUsuario ORDER BY a.id DESC");
        query.setParameter("idUsuario", u);
        List<Mensaje> list = (List<Mensaje>)query.getResultList();
        return list;
    }
    
    @Override
    public List<Mensaje> BuscarMensajeTodos(int u)
    {
        Query query = em.createNativeQuery("SELECT * FROM Mensaje WHERE id_Usuario ='"+u+"' OR id_Usuario IN(SELECT id_Seguidor FROM Seguidos WHERE id_Usuario ='"+u+"') ORDER BY id DESC",Mensaje.class );
        List<Mensaje> list = query.getResultList();
        return list;
    }
    
    
    @Override
    public boolean ValidarMensaje(int id,Usuarios u)
    {
    Query query = em.createQuery("SELECT a FROM Mensaje a WHERE a.idUsuario = :idUsuario AND a.id = :id");
        query.setParameter("idUsuario", u);
        query.setParameter("id", id);
        if (query.getResultList().size() > 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean EditarMensaje(int id,String mensaje)
    {
    int query = em.createNativeQuery("UPDATE mensaje SET mensaje='"+mensaje+"' WHERE id ='"+id+"' ").executeUpdate();
        
        if (query > 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public Mensaje BuscarUnoMensaje(int u)
    { 
        Query query = em.createQuery("SELECT a FROM Mensaje a WHERE a.id = :mensaje");
        query.setParameter("mensaje", u);
        Mensaje list = (Mensaje)query.getSingleResult();
        return list;
    }
    
    @Override
    public List<Mensaje> LikeMensaje(String mensaje) {
       Query query = em.createNativeQuery("SELECT * FROM Mensaje  WHERE mensaje LIKE '%"+mensaje+"%' ",Mensaje.class);
        List<Mensaje> a= query.getResultList();
        return a;
    }
    
    @Override
    public List<Mensaje> LikeHashtag(String mensaje) {
       Query query = em.createNativeQuery("SELECT * FROM Mensaje  WHERE mensaje LIKE '%#"+mensaje+"%' ",Mensaje.class);
        List<Mensaje> a= query.getResultList();
        return a;
    }
    
}
