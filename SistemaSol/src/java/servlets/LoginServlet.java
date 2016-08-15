package servlets;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author maa
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op.equals("cad")) {
            HttpSession session = request.getSession(false);
            response.sendRedirect("cadastro.jsp");

        } else if (op.equals("download")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("isLogado", true);
            session.setAttribute("listaDownloadVazia", false);
            response.sendRedirect("download.jsp");

        } else if (op.equals("contato")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("isLogado", true);
            response.sendRedirect("contato.jsp");

        } else if (op.equals("perfil")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("isLogado", true);
            session.setAttribute("editarPerfil", false);
            response.sendRedirect("perfil.jsp");

        } else if ("sair".equals(op)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        HttpSession session = request.getSession();

        String n = request.getParameter("username");
        String p = request.getParameter("userpass");

        try {
            if (DAO.validaUsuario(n, p)) {
                Usuario u = DAO.buscaNomeUsuario(n);
                session.setAttribute("nome_usuario", u.getNome());
                session.setAttribute("email_usuario", u.getEmail());
                session.setAttribute("senha_usuario", u.getSenha());
                session.setAttribute("isLogado", true);
                response.sendRedirect("home.jsp");

            } else {
                request.setAttribute("erroLogin", "Usuário e/ou senha incorretos!");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);

            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
