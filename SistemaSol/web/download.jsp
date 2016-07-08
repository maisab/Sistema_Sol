<%@page import="dao.DAO"%>
<%@page import="java.util.List"%>
<%
    if (session.getAttribute("isLogado") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script language="JavaScript">

        function mascara(t, mask) {
            var i = t.value.length;
            var saida = mask.substring(1, 0);
            var texto = mask.substring(i)
            if (texto.substring(0, 1) != saida) {
                t.value += texto.substring(0, 1);
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Download</title>
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="css/sb-admin.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Download Dados</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%=session.getAttribute("nome_usuario")%> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#"><i class="fa fa-fw fa-user"></i> Perfil</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="LoginServlet?op=sair"><i class="fa fa-fw fa-power-off"></i>Sair</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li>
                            <a href="home.jsp"><i class="fa fa-fw fa-home"></i> Inicio</a>
                        </li>
                        <li class="active">
                            <a href="#"><i class="fa fa-fw fa-download"></i> Download</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">

                            <div id="form-download" style="margin-left: 300px; margin-top: 50px;"> 
                                <h2>Selecione os dados para Download</h2>

                                <%
                                    if ((Boolean) session.getAttribute("listaDownloadVazia")) {
                                        out.print("<br/><b style=\"color:red\">A pesquisa não retornou dados!</b><br/><br/>");
                                    }
                                %>

                                <div id="tabela" >
                                    <form action="DownloadServlet" method="GET">
                                        <table style="margin-left: 40px; margin-top: 10px">
                                            <tr rows>
                                                <td  style="width: 100px; height: 45px;">
                                                    <b>Data Inicial:</b>
                                                </td> 
                                                <td >
                                                    <select name="data_inicial" style="width: 250px; height: 25px;">
                                                        <%
                                                            List<String> datas = DAO.listaDatas();
                                                            out.println("<option value=\"-1\"> Escolha uma data e hora inicial </option>");
                                                            for (String data : datas) {
                                                                String dataFormatada = data.split(" ")[0];
                                                                dataFormatada = "Data: " + dataFormatada.split("-")[2] + "/" + dataFormatada.split("-")[1] + "/" + dataFormatada.split("-")[0] + " Hora: " + data.split(" ")[1].substring(0, 8);
                                                                out.println("<option value=\"" + data + "\">" + dataFormatada + "</option>");
                                                            }
                                                        %>
                                                    </select>
                                            </tr>
                                            <tr>
                                                <td  style="width: 100px; height: 45px;">
                                                    <b>Data Final:</b>
                                                </td>
                                                <td>
                                                    <select name="data_final" style="width: 250px; height: 25px;">
                                                        <%
                                                            out.println("<option value=\"-1\"> Escolha uma data e hora final </option>");
                                                            for (String data : datas) {
                                                                String dataFormatada = data.split(" ")[0];
                                                                dataFormatada = "Data: " + dataFormatada.split("-")[2] + "/" + dataFormatada.split("-")[1] + "/" + dataFormatada.split("-")[0] + " Hora: " + data.split(" ")[1].substring(0, 8);
                                                                out.println("<option value=\"" + data + "\">" + dataFormatada + "</option>");
                                                            }
                                                        %>
                                                    </select>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td  style="width: 100px; height: 45px;">
                                                    <b>Sensor:</b>
                                                </td>
                                                <td>
                                                    <select name="sensor" style="width: 250px; height: 25px;">
                                                        <%
                                                            out.println("<option value=\"-1\"> Escolha um tipo de sensor </option>");
                                                            out.println("<option value=\"1\"> 1   - Sensor de Corrente </option>");
                                                            out.println("<option value=\"2\"> 2   - Sensor de Corrente </option>");
                                                            out.println("<option value=\"3\"> 3   - Sensor de Corrente </option>");
                                                            out.println("<option value=\"4\"> 4   - Sensor de Corrente </option>");
                                                            out.println("<option value=\"5\"> 5   - Sensor de Corrente </option>");
                                                            out.println("<option value=\"6\"> 6   - Sensor de Tensão </option>");
                                                            out.println("<option value=\"7\"> 7   - Sensor de Tensão </option>");
                                                            out.println("<option value=\"8\"> 8   - Sensor de Tensão </option>");
                                                            out.println("<option value=\"9\"> 9   - Sensor de Tensão </option>");
                                                            out.println("<option value=\"10\"> 10 - Sensor de Tensão </option>");
                                                            out.println("<option value=\"11\"> 11 - Sensor de Tensão </option>");
                                                        %>
                                                    </select>
                                                    <!--<input type="text" name="sensor" onkeypress="mascara(this, '#')" maxlength="1" required/>-->
                                                </td>
                                            </tr>

                                            <tr>
                                                <td colspan="2">
                                                    <input type="submit" value="Download" name="op" style="width: 150px; height: 25px; margin-left: 110px; margin-top: 15px;">
                                                    <!--<input type="reset" value="Limpar">-->
                                                </td>
                                            </tr>
                                        </table>
                                    </form>

                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
