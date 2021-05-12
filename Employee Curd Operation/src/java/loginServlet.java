
import DAO.LoginDAO;
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

@WebServlet(urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            String username = request.getParameter("uname");
            String password = request.getParameter("pwd");

            if (LoginDAO.isUser(username, password)) {
                session.setAttribute("username", username);
                RequestDispatcher dispatch = request.getRequestDispatcher("nav.html");
                dispatch.include(request, response);
                out.println("<div class=\"container mt-2\">");
                out.println("<h3>Admin Section</h3>");
                out.println("<ul class=\"actions\">");
                out.println("<li>Add Librarian</li>");
                out.println("<li>Edit Librarian</li>");
                out.println("<li>View Librarian</li>");
                out.println("<li>Delete Librarian</li>");
                out.println("<li>Logout</li>");
                out.println("</ul>");
                out.println("</div>");
            } else {
                out.println("<h3 class=\"text-danger text-center\">Username or Password is incorrect!</h3>");
                request.getRequestDispatcher("index.html").include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
