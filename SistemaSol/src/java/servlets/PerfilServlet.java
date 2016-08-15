/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maa
 */
public class PerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op.equals("editarP")) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogado", true);
            session.setAttribute("editarPerfil", true);
            response.sendRedirect("perfil.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("isLogado", true);

        String nome = request.getParameter("nome_usuario");
        String senha = request.getParameter("senha_usuario");
        String email = request.getParameter("email_usuario");

       DAO.atualizarUsuario(nome, senha, email);
       
        session.setAttribute("nome_usuario", nome);
        session.setAttribute("senha_usuario", senha);
        session.setAttribute("editarPerfil", false);
        response.sendRedirect("perfil.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
