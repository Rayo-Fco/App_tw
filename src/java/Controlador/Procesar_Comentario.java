/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejb.ComentarioFacadeLocal;
import Ejb.MensajeFacadeLocal;
import Modelo.Comentario;
import Modelo.Mensaje;
import Modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Francisco
 */
public class Procesar_Comentario extends HttpServlet {

    @EJB(name = "Men")
    private MensajeFacadeLocal men;

    @EJB(name = "Com")
    private ComentarioFacadeLocal com;

    
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
        String idcomen = request.getParameter("idcomentario");
        String comentario = request.getParameter("txtComentario");
         Usuarios u = (Usuarios)request.getSession().getAttribute("Usuario");
         int id = u.getId();
         if(idcomen == null || comentario == null || u == null)
        {
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
       
        try {
            int p = Integer.parseInt(idcomen);
            Mensaje m = men.BuscarUnoMensaje(p);
            Comentario c = new Comentario();
            c.setComentario(comentario);
            c.setIdUsuario(u);
            String f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Timestamp(new java.util.Date().getTime()));
            c.setFechaHora(f);
            c.setIdMensaje(m);
            com.create(c);
            request.getSession().setAttribute("ListaMensaje", men.BuscarMensajeTodos(id));
            request.getRequestDispatcher("index.jsp").forward(request, response); 
        } 
        catch (NumberFormatException e) 
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
