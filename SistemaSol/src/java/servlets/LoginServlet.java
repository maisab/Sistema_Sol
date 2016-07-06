/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DAO;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Sensor;
import model.Usuario;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op.equals("cad")) {
            HttpSession session = request.getSession(false);
            response.sendRedirect("cadastro.jsp");
            return;

        } else if (op.equals("download")) {
            HttpSession session = request.getSession(false);
            response.sendRedirect("download.jsp");
            return;

        } else if (op.equals("Download")) {
            String dataInicial = request.getParameter("data_inicial"); //nome do campo no formulario
            String horaInicial = request.getParameter("hora_inicial");
            String dataFinal = request.getParameter("data_final"); //nome do campo no formulario
            String horaFinal = request.getParameter("hora_final");
            int sensor = Integer.parseInt(request.getParameter("sensor"));

            try {
                if (DAO.buscaSensor(dataInicial, horaInicial, dataFinal, horaFinal, sensor).isEmpty()) {
                    System.out.println("vazia");
                } else {
                    List<String> lista = DAO.buscaSensor(dataInicial, horaInicial, dataFinal, horaFinal, sensor);
                    System.out.println("LISTA : " + lista.toString());

                    File arquivo = new File("arquivo.csv");
                    FileWriter fw = new FileWriter(arquivo);
                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write("datahora_coleta_dados;sensor_id_sensor;dados_coleta_dados");
                    bw.newLine();

                    for (String linha : lista) {
                        System.out.println("LINHA -- " + linha);
                        bw.write(linha);
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();

                    //tipo de arquivo
                    response.setContentType("application/csv");

                    //nome do arquivo a ser exibido na caixa de download do browser
                    response.setHeader("Content-Disposition", "attachment; filename=arquivo.csv;");

                    //manda os bytes do arquivo pelo response
                    URL url = arquivo.toURI().toURL();
                    BufferedInputStream leitor = new BufferedInputStream(url.openStream(), 4 * 1024);
                    OutputStream escritor = response.getOutputStream();
                    byte[] buffer = new byte[4 * 1024];
                    int size = 0;
                    while ((size = leitor.read(buffer, 0, buffer.length)) != -1) {
                        escritor.write(buffer, 0, size);
                    }
                    escritor.close();
                    leitor.close();
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
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println("ENTROU LOGIN---- ");
        System.out.println(request.getParameter("op"));
        String op = request.getParameter("op");

        if (op.equals("Login")) {
            HttpSession session = request.getSession(true);

            String n = request.getParameter("username"); //nome do campo no formulario
            String p = request.getParameter("userpass");

            if (session != null) {
                session.setAttribute("nome_usuario", n);
            }

            try {
                if (DAO.validate(n, p)) {
                    session.setAttribute("isLogado", true);
                    RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                    rd.forward(request, response);

                } else {

                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.close();

        } else if (op.equals("Cadastro")) {
            HttpSession session = request.getSession(true);
            String nome = request.getParameter("nome_usuario"); //nome do campo no formulario
            String email = request.getParameter("email_usuario");
            String senha = request.getParameter("senha_usuario");

            Usuario u = new Usuario();
            u.setNome(nome);
            u.setEmail(email);
            u.setSenha(senha);

            try {
                DAO.insert(u);
                session.setAttribute("isLogado", true);
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

//        } else if (op.equals("Download")) {
////            System.out.println("entrou download");
////            System.out.println(request.getParameter("op"));
//
//            String dataInicial = request.getParameter("data_inicial"); //nome do campo no formulario
//            String horaInicial = request.getParameter("hora_inicial");
//            String dataFinal = request.getParameter("data_final"); //nome do campo no formulario
//            String horaFinal = request.getParameter("hora_final");
//            int sensor = Integer.parseInt(request.getParameter("sensor"));
//
////            System.out.println(horaInicial);
////            System.out.println(dataFinal);
////            System.out.println(horaFinal);
////            System.out.println(sensor);
////
//            try {
//                if (DAO.buscaSensor(dataInicial, horaInicial, dataFinal, horaFinal, sensor).isEmpty()) {
//                    System.out.println("vazia");
//                } else {
//                    List<String> lista = DAO.buscaSensor(dataInicial, horaInicial, dataFinal, horaFinal, sensor);
//                    System.out.println("LISTA : " + lista.toString());
//
//                    File arquivo = new File("arquivo.csv");
//                    FileWriter fw = new FileWriter(arquivo);
//                    BufferedWriter bw = new BufferedWriter(fw);
//
//                    bw.write("datahora_coleta_dados;sensor_id_sensor;dados_coleta_dados");
//                    bw.newLine();
//
//                    for (String linha : lista) {
//                        System.out.println("LINHA -- " + linha);
//                        bw.write(linha);
//                        bw.newLine();
//                    }
//                    bw.close();
//                    fw.close();
//
////                    InputStream is = null;
////                    OutputStream os = null;
////
////                    is = new DataInputStream(new FileInputStream(arquivo));
////                    response.setContentLength((int) arquivo.length());
////                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "arquivo.csv" + "\"");
////                    os = response.getOutputStream();
////
////                    /*Envia arquivo*/
////                    int read = 0;
////                    byte[] bytes = new byte[1024];
////                    while ((read = is.read(bytes)) != -1) {
////                        os.write(bytes, 0, read);
////                    }
////                    os.flush();
////                    FileReader fr = new FileReader("arquivo.csv");
////                    BufferedReader br = new BufferedReader(fr);
////                    
////                    String l = br.readLine();
////                    
////                    while(l != null){
////                        System.out.println(l);
////                        l = br.readLine();
////                    }
//                }
//
////            System.out.println("TEMPO : " + Time.valueOf(horaInicial));
////            dataInicial = dataInicial + " " + horaInicial;
////            dataFinal = dataFinal + " " + horaFinal;
////            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
////            java.sql.Date data = new java.sql.Date(format.parse(dataStr).getTime());
////            java.util.Date dt = new java.util.Date();
////
////            java.text.SimpleDateFormat sdf
////                    = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////            String currentTime = sdf.format(dt);
////            System.out.println("DATA INICIAL" + dataInicial);
////            ArrayLis  DAO.buscaSensor(dataInicial, horaInicial, dataFinal, horaFinal, sensor);
//            } catch (SQLException ex) {
//                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (InstantiationException ex) {
//                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

//        System.out.println("SAIU LOGIN---- ");
    }

}
