/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.LibrarianDAO;
import POJO.LibrarianPOJO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Smit Bhikadiya
 */
@WebServlet(urlPatterns = {"/editServlet"})
public class editServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           int id = Integer.parseInt(request.getParameter("id"));
           
            try {
                LibrarianPOJO pojo = LibrarianDAO.getById(id);
                request.getRequestDispatcher("nav.html").include(request, response);
                    out.println("<div class='container mt-2 p-3'> ");
                    out.println("       <form action=\"editServlet\" method=\"POST\">                                                    ");
                    out.println("           <input type='hidden' name='id' value="+pojo.getId()+"> ");
                    out.println("           <div class=\"text-center text-dark logo mb-2\">                                                     ");
                    out.println("               <h2>Update Librarian</h2>                                                                          ");
                    out.println("           </div>                                                                                            ");
                    out.println("           <div class=\"form-group\">                                                                          ");
                    out.println("               <label for='name'>Name</label>                                                                ");
                    out.println("               <input type='text' class='form-control' placeholder='Name' name='name' value="+pojo.getName()+" required>                       ");
                    out.println("           </div>                                                                                            ");
                    out.println("            <div class='form-group'>                                                                         ");
                    out.println("               <label for=\"email\">Email</label>                                                              ");
                    out.println("               <input type=\"email\" class=\"form-control\" placeholder=\"Email\" name=\"email\" value="+pojo.getEmail()+" required>                    ");
                    out.println("           </div>                                                                                            ");
                    out.println("           <div class=\"form-group\">                                                                          ");
                    out.println("               <label for=\"password\">Password</label>                                                        ");
                    out.println("               <input type=\"password\" class=\"form-control\" placeholder=\"Password\"name=\"pwd\" value="+pojo.getPassword()+" required>                 ");
                    out.println("           </div>                                                                                            ");
                    out.println("           <div class=\"form-group\">                                                                          ");
                    out.println("               <label for=\"mobile\">Mobile</label>                                                            ");
                    out.println("               <input type=\"number\" max=\"9999999999\" class=\"form-control\" placeholder=\"mobile\" name=\"mobile\" value="+pojo.getMobile()+" required> ");
                    out.println("           </div>                                                                                            ");
                    out.println("           <div class=\"form-group\">                                                                          ");
                    out.println("               <input type=\"submit\" class=\"btn btn-success\" value=\"Update\">                                  ");
                    out.println("           </div>                                                                                            ");
                    out.println("      </form>                                                                                               ");
                    out.println("</div>                                                                                                    ");
            } catch (SQLException ex) {
                Logger.getLogger(editServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LibrarianPOJO pojo = new LibrarianPOJO();
        pojo.setId(Integer.parseInt(request.getParameter("id")));
        pojo.setName(request.getParameter("name"));
        pojo.setEmail(request.getParameter("email"));
        pojo.setPassword(request.getParameter("pwd"));
        pojo.setMobile(Long.parseLong(request.getParameter("mobile")));
        
        try {
            LibrarianDAO.update(pojo);
            response.sendRedirect("ViewRecordsServlet");
        } catch (SQLException ex) {
            Logger.getLogger(editServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
