package servlets;

import dao.DAO;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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
            String hrInicial = request.getParameter("hora_inicial"); //nome do campo no formulario
            String dataFinal = request.getParameter("data_final"); //nome do campo no formulario            
            String hrFinal = request.getParameter("hora_final"); //nome do campo no formulario            
            int sensor = Integer.parseInt(request.getParameter("sensor"));

            String dtInicial = dataInicial.split("-")[2] + "-" + dataInicial.split("-")[1] + "-" + dataInicial.split("-")[0]
                    + " " + hrInicial;
            String dtFinal = dataFinal.split("-")[2] + "-" + dataFinal.split("-")[1] + "-" + dataFinal.split("-")[0]
                    + " " + hrFinal;

            try {
                if (DAO.buscaSensor(dtInicial, dtFinal, sensor).isEmpty()) {
                    session.setAttribute("isLogado", true);
                    session.setAttribute("listaDownloadVazia", true);
                    response.sendRedirect("download.jsp");

                } else {
                    session.setAttribute("isLogado", true);
                    session.setAttribute("listaDownloadVazia", false);

                    List<String> lista = DAO.buscaSensor(dtInicial, dtFinal, sensor);
//                    File arquivo = new File("/SistemaSol/web/arquivo.csv");
//                    File arquivo = new File(getClass().getResource("web/arquivo.txt").toString());
//                    ServletContext app = (servlet).getServletContext();
                    ServletContext servletContext = getServletContext();
                    File tmpDir = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                    File arquivo = new File(tmpDir, "arquivo.csv");

//                    if (arquivo.exists()) {
//                        arquivo.delete();
//                        System.out.println("ENtrou no existe");
//                    }
//                    arquivo.createNewFile();

                    FileWriter fw = new FileWriter(arquivo, false);
                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write("datahora_coleta_dados;sensor_id_sensor;dado_coleta_dados");
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
