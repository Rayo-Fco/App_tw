/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

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
public class SeguidosFacade extends AbstractFacade<Seguidos> implements SeguidosFacadeLocal {

    @PersistenceContext(unitName = "App_twPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguidosFacade() {
        super(Seguidos.class);
    }
    
    @Override
    public List<Seguidos> BuscarSeguidos(Usuarios usuario)
    {   Query query = em.createQuery("SELECT a FROM Seguidos a WHERE a.idUsuario = :usu");
        query.setParameter("usu", usuario);
        List<Seguidos> list = (List<Seguidos>)query.getResultList();
        return list;
    }
    
    
    @Override
    public List<Seguidos> BuscarSeguidores(Usuarios usuario)
    { Query query = em.createQuery("SELECT a FROM Seguidos a WHERE a.idSeguidor = :idUsuario");
        query.setParameter("idUsuario", usuario);
        List<Seguidos> list = (List<Seguidos>)query.getResultList();
        return list;
    }
    
    
    @Override
    public Seguidos BuscarIDSeguido(Usuarios usuario,Usuarios seguidor)
    {   Query query = em.createQuery("SELECT a FROM Seguidos a WHERE a.idUsuario = :usu AND a.idSeguidor = :seg");
        query.setParameter("usu", usuario);
        query.setParameter("seg", seguidor);
         Seguidos u = null;
        if (query.getResultList().size() > 0) {
            List<Seguidos> list = (List<Seguidos>)query.getResultList();
            u = new Seguidos();
            u.setId(list.get(0).getId());
            u.setIdSeguidor(list.get(0).getIdSeguidor());
            u.setIdUsuario(list.get(0).getIdUsuario());
            return u;
            
        }
        return u;
    }
}
