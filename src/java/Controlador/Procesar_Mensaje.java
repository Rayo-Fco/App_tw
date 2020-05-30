/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejb.MensajeFacadeLocal;
import Modelo.Mensaje;
import Modelo.Usuarios;
import java.io.IOException;
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
public class Procesar_Mensaje extends HttpServlet {

    @EJB(name = "Mensaje")
    private MensajeFacadeLocal mensaje;

    
     
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
        String Mensaje = request.getParameter("txtMensaje");
    
        if(Mensaje == null)
        {
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        if (Mensaje.trim().equals("") ) {
            request.getSession().setAttribute("mensaje", "Error!! Tiene Que Llenar el Campo del Post");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            request.getSession().setAttribute("mensaje", "");
        }
        else
        {
            Usuarios u = (Usuarios)request.getSession().getAttribute("Usuario");
            Mensaje m = new Mensaje();
            m.setMensaje(Mensaje);
            String f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Timestamp(new java.util.Date().getTime()));
            m.setFechaMensaje(f);
            m.setIdUsuario(u);
            mensaje.create(m);
            request.getSession().setAttribute("ListaMensaje", mensaje.BuscarMensajeTodos(u.getId()));
            request.getSession().setAttribute("MisMensajes", mensaje.BuscarMensaje(u));
            request.getSession().setAttribute("Total", mensaje.BuscarMensaje(u).size());
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
