/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejb.ComentarioFacadeLocal;
import Ejb.MensajeFacadeLocal;
import Modelo.Comentario;
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
public class Procesar_EditarComentario extends HttpServlet {

    @EJB(name = "Com")
    private ComentarioFacadeLocal com;
    
    @EJB(name = "men")
    private MensajeFacadeLocal men;
    
    
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
        String id = request.getParameter("txtID");
        String comentario = request.getParameter("txtComentario");
        
        if(id == null || comentario == null)
        {
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        try {
            
        Usuarios u = (Usuarios)request.getSession().getAttribute("Usuario");
        String respuesta = "";
        int p = Integer.parseInt(id);
        
             if(com.ValidarComentario(p, u))
            {
                Comentario r = com.BuscarUnoComentario(p);
                r.setComentario(comentario);
                com.edit(r);
                respuesta = "Comentario Editado";
                request.getSession().setAttribute("ListaMensaje", men.BuscarMensajeTodos(u.getId()));
            }
            else
            {
                respuesta = "Error!! Usted no puede Editar el Comentario";
            }    
        
            request.getSession().setAttribute("respuestaeditar", respuesta );
            request.getRequestDispatcher("Si/respuesta.jsp").forward(request, response); 
        } 
        catch (NumberFormatException e) 
        {
            String  respuesta = "Error!! Usted no puede Editar el Comentario";
            request.getSession().setAttribute("respuestaeditar", respuesta );
            request.getRequestDispatcher("Si/respuesta.jsp").forward(request, response); 
            
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
