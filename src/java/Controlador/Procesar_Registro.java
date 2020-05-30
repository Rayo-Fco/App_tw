/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejb.SeguidosFacadeLocal;
import Ejb.UsuariosFacadeLocal;
import Modelo.Usuarios;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Francisco
 */
public class Procesar_Registro extends HttpServlet {

    @EJB(name = "seg")
    private SeguidosFacadeLocal seg;

    @EJB
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
        String nombre = request.getParameter("txtNombre");
        String edad = request.getParameter("txtEdad");
        String clave = request.getParameter("txtPass");
        
        String mensaje ="";
        String mensajeExito = "";
        
        int ed = 0;
        
          Usuarios a1 = new Usuarios();
            a1.setNick(nick);
            a1.setNombre(nombre);
            a1.setClave(clave);
        
        if (nick.trim().equals("") || nombre.trim().equals("") || edad.trim().equals("") || clave.trim().equals("") ) {
            mensaje = "Complete los campos";
            request.getSession().setAttribute("registro", mensaje);
            request.getRequestDispatcher("registrar.jsp").forward(request, response);
        }else{
           
            try {
                ed = Integer.parseInt(edad);
                a1.setEdad(ed);
                if(!usu.BuscarUsuario(nick))
                {
                    if(ed > 14 && ed< 99)
                    {
                    usu.create(a1);
                    mensajeExito = "Usuario creado correctamente";
                    request.getSession().setAttribute("registro", mensajeExito);
                    request.getSession().setAttribute("Usuario", a1);
                    request.getSession().setAttribute("Seguidores", seg.BuscarSeguidores(a1).size() );
                    request.getSession().setAttribute("Seguidos", seg.BuscarSeguidos(a1).size() );
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    else
                    {
                        if(ed <1 || ed >99  )
                        {
                            mensaje = "Error!! Edad Invalida";
                        }
                        else
                        {
                             mensaje = "Error!! Edad Permitida Mayor de 14 Años";
                        }
                        request.getSession().setAttribute("registro", mensaje);
                        request.getSession().setAttribute("campo", a1);
                        request.getRequestDispatcher("registrar.jsp").forward(request, response);
                        request.getSession().setAttribute("registro", "");
                        request.getSession().setAttribute("campo", null);
                    }
                    
                }
                else
                {
                    mensaje = "Error!! Nick Ya se Encuentra Registrado";
                    request.getSession().setAttribute("registro", mensaje);
                    request.getSession().setAttribute("campo", a1);
                    request.getRequestDispatcher("registrar.jsp").forward(request, response);
                    request.getSession().setAttribute("registro", "");
                    request.getSession().setAttribute("campo", null);
                }
                
            } catch (NumberFormatException e) {
                mensaje = "Ingrese solo números en el campo Edad";
                request.getSession().setAttribute("registro", mensaje);
                    request.getSession().setAttribute("campo", a1);
                    request.getRequestDispatcher("registrar.jsp").forward(request, response);
                    request.getSession().setAttribute("registro", "");
                    request.getSession().setAttribute("campo", null);
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
