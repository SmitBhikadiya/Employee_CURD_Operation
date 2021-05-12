
import DAO.LibrarianDAO;
import POJO.LibrarianPOJO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/ViewRecordsServlet"})
public class ViewRecordsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            if (username == null || username == "") {
                response.sendRedirect("index.html");
            } else {
                List<LibrarianPOJO> rows = null;
                try {
                    String search = (String) session.getAttribute("search");
                    if (search != null) {
                        search = (String) session.getAttribute("search");
                        rows = LibrarianDAO.search(search);
                    }
                    else{
                        rows = LibrarianDAO.select();
                    }
                    
                    request.getRequestDispatcher("nav.html").include(request, response);
                    
                    out.println("<div class=\"container mt-3 text-light\">");
                    out.println("<table class=\"table table-borderless\">");
                    out.println("<thead class=\"thead-dark\">");
                    out.println("<tr>");
                    out.println("<th>ID</th>");
                    out.println("<th>Name</th>");
                    out.println("<th>Email</th>");
                    out.println("<th>Password</th>");
                    out.println("<th>Mobile</th>");
                    out.println("<th>Edit</th>");
                    out.println("<th>Delete</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    int i = 1;
                    for (LibrarianPOJO row : rows) {
                        out.println("<tr>");
                        out.println("<td>" + i++ + "</td>");
                        out.println("<td>" + row.getName() + "</td>");
                        out.println("<td>" + row.getEmail() + "</td>");
                        out.println("<td>" + row.getPassword() + "</td>");
                        out.println("<td>" + row.getMobile() + "</td>");
                        out.println("<td><a href='editServlet?id=" + row.getId() + "'>Edit</a></td>");
                        out.println("<td><a href='deleteServlet?id=" + row.getId() + "'>Delete</a></td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println("</div>");

                } catch (SQLException ex) {
                    Logger.getLogger(ViewRecordsServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    private void Attribute(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
