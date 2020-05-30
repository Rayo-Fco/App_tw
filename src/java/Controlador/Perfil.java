/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejb.MensajeFacadeLocal;
import Ejb.SeguidosFacadeLocal;
import Ejb.UsuariosFacadeLocal;
import Modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Francisco
 */
public class Perfil extends HttpServlet {

    @EJB(name = "seg")
    private SeguidosFacadeLocal seg;

    @EJB(name = "men")
    private MensajeFacadeLocal men;

    @EJB(name = "Usu")
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
        String nick = request.getParameter("Perfil");
        
        if(nick == null)
        {
            
        }
            if(usu.BuscarUsuario(nick))
            {
                Usuarios u2 = (Usuarios)request.getSession().getAttribute("Usuario");
                
                request.getSession().setAttribute("BusquedaPerfilMensaje", men.BuscarMensaje(usu.VerUsuarioNick(nick)));
                request.getSession().setAttribute("BusquedaPerfil", usu.VerUsuarioNick(nick));
                request.getSession().setAttribute("Seguidos2", seg.BuscarSeguidos(u2) );
                request.getRequestDispatcher("No/Perfiles.jsp").forward(request, response);
                request.getSession().setAttribute("BusquedaPerfilMensaje", null);
            }
            else
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
