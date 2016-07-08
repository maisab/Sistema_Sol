package servlets;

import dao.DAO;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maa
 */
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        HttpSession session = request.getSession();

        if (op.equals("Download")) {
            String dataInicial = request.getParameter("data_inicial"); //nome do campo no formulario
            String dataFinal = request.getParameter("data_final"); //nome do campo no formulario            
            int sensor = Integer.parseInt(request.getParameter("sensor"));

            String dtInicial = dataInicial.split(" ")[0];
            String hrInicial = dataInicial.split(" ")[1].substring(0, 8);

            String dtFinal = dataFinal.split(" ")[0];
            String hrFinal = dataFinal.split(" ")[1].substring(0, 8);

            try {
                if (DAO.buscaSensor(dtInicial, hrInicial, dtFinal, hrFinal, sensor).isEmpty()) {
                    session.setAttribute("isLogado", true);
                    session.setAttribute("listaDownloadVazia", true);
                    response.sendRedirect("download.jsp");

                } else {
                    session.setAttribute("isLogado", true);
                    session.setAttribute("listaDownloadVazia", false);
                    List<String> lista = DAO.buscaSensor(dtInicial, hrInicial, dtFinal, hrFinal, sensor);
                    File arquivo = new File("arquivo.csv");
                    FileWriter fw = new FileWriter(arquivo);
                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write("datahora_coleta_dados;sensor_id_sensor;dados_coleta_dados");
                    bw.newLine();

                    for (String linha : lista) {
                        bw.write(linha);
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();

                    //tipo de arquivo
                    response.setContentType("application/csv");
                    //nome do arquivo a ser exibido na caixa de download
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
