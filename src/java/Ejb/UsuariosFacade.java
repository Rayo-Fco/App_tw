/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

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
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "App_twPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    
    @Override
    public boolean ValidarUsuario(String nick,String clave)
    { Query query = em.createQuery("SELECT a FROM Usuarios a WHERE a.nick = :nick AND a.clave = :clave");
        query.setParameter("nick", nick);
        query.setParameter("clave", clave);
        
        if (query.getResultList().size() > 0) {
            
            return true;
        }
        
        
        return false;
    }
    
    @Override
    public boolean BuscarUsuario(String nick)
    { Query query = em.createQuery("SELECT a FROM Usuarios a WHERE a.nick = :nick");
        query.setParameter("nick", nick);
        if (query.getResultList().size() > 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public Usuarios VerUsuario(String nick,String clave)
    { Query query = em.createQuery("SELECT a FROM Usuarios a WHERE a.nick = :nick AND a.clave = :clave");
        query.setParameter("nick", nick);
        query.setParameter("clave", clave);
        Usuarios u = null;
        if (query.getResultList().size() > 0) {
            List<Usuarios> list = (List<Usuarios>)query.getResultList();
            u = new Usuarios();
            u.setId(list.get(0).getId());
            u.setNick(list.get(0).getNick());
            u.setNombre(list.get(0).getNombre());
            u.setEdad(list.get(0).getEdad());
            u.setClave(list.get(0).getClave());
            return u;
        }
        return u;
    }
    
     @Override
    public List<Usuarios> LikeUsuario(String nick) {
       Query query = em.createNativeQuery("SELECT * FROM Usuarios  WHERE nick LIKE '"+nick+"%' ORDER BY nick",Usuarios.class);
        List<Usuarios> artistes= query.getResultList();
        return artistes;
    }
        
    @Override
    public Usuarios VerUsuarioNick(String nick)
    { Query query = em.createQuery("SELECT a FROM Usuarios a WHERE a.nick = :nick ");
        query.setParameter("nick", nick);
        Usuarios u = null;
        if (query.getResultList().size() > 0) {
            List<Usuarios> list = (List<Usuarios>)query.getResultList();
            u = new Usuarios();
            u.setId(list.get(0).getId());
            u.setNick(list.get(0).getNick());
            u.setNombre(list.get(0).getNombre());
            u.setEdad(list.get(0).getEdad());
            u.setClave(list.get(0).getClave());
            return u;
        }
        return u;
    }
    
}