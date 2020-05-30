/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejb.MensajeFacadeLocal;
import Ejb.SeguidosFacadeLocal;
import Ejb.UsuariosFacadeLocal;
import Modelo.Mensaje;
import Modelo.Seguidos;
import Modelo.Usuarios;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Francisco
 */
public class Procesar_Login extends HttpServlet {

    @EJB(name = "men")
    private MensajeFacadeLocal men;

    @EJB(name = "seguidores")
    private SeguidosFacadeLocal seguidores;

    

    @EJB(name = "usu")
    private UsuariosFacadeLocal usu;
    
    
    
    
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        String nick = request.getParameter("txtNick");
        String clave = request.getParameter("txtPass");
        if(nick == null || clave == null)
        {
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        
        String mensaje ="";
        
         
        
        if (nick.trim().equals("") || clave.trim().equals("") ) {
            mensaje = "Complete los campos";
            request.getSession().setAttribute("registro", mensaje);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            request.getSession().setAttribute("registro", "");
        }else{
            Usuarios a1 = new Usuarios();
            a1.setNick(nick);
            a1.setClave(clave);
                if(usu.ValidarUsuario(nick, clave))
                {
                    Usuarios u = usu.VerUsuario(nick, clave);
                    List<Seguidos> Seguidos = seguidores.BuscarSeguidos(u);
                    List<Seguidos> Seguidores = seguidores.BuscarSeguidores(u);
                    List<Mensaje> mensa = men.BuscarMensajeTodos(u.getId());
                    request.getSession().setAttribute("ListaMensaje", mensa);
                    request.getSession().setAttribute("Total", men.BuscarMensaje(u).size());
                    request.getSession().setAttribute("Usuario", u);
                    request.getSession().setAttribute("Seguidores", Seguidores.size() );
                    request.getSession().setAttribute("Seguidos", Seguidos.size() );
                    request.getSession().setAttribute("Seguidos2", seguidores.BuscarSeguidos(u) );
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                else
                {
                    mensaje = "Error!! Nick/Clave Invalida";
                    request.getSession().setAttribute("registro", mensaje);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    request.getSession().setAttribute("registro", "");
                }
            
            
        }        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
