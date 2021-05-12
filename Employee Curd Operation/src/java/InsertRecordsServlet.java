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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Smit Bhikadiya
 */
@WebServlet(urlPatterns = {"/InsertRecordsServlet"})
public class InsertRecordsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if(session.getAttribute("username").equals("")){
                response.sendRedirect("index.html");
            }
            else{
                RequestDispatcher dispatch1,dispatch2;
                dispatch1 = request.getRequestDispatcher("nav.html");
                dispatch1.include(request, response);
                dispatch2 = request.getRequestDispatcher("insert.html");
                dispatch2.include(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String password = request.getParameter("pwd");
            String email = request.getParameter("email");
            long mobile = Long.parseLong(request.getParameter("mobile"));
            LibrarianPOJO pojo = new LibrarianPOJO(name,email,password,mobile);
            int result = 0;
            try {
                result = LibrarianDAO.insert(pojo);
            } catch (SQLException ex) {
                Logger.getLogger(InsertRecordsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.getRequestDispatcher("nav.html").include(request, response);
            out.println("<h3 class='text-center mt-2 text-success'>"+result+" Librarian Added Succesfully</h3>");
            request.getRequestDispatcher("insert.html").include(request, response);
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
