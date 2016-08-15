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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Contato</title>
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
                                <a href="LoginServlet?op=perfil"><i class="fa fa-fw fa-user"></i> Perfil</a>
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
                        <li>
                            <a href="LoginServlet?op=download"><i class="fa fa-fw fa-download"></i> Download</a>
                        </li>
                        <li class="active">
                            <a href="#"><i class="fa fa-fw fa-book"></i> Contato</a>
                        </li>

                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-info-circle"></i> Sobre <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="demo" class="collapse">
                                <li>
                                    <a href="#">Cris Halmeman</a>
                                </li>
                                <li>
                                    <a href="#">Radames Halmeman</a>
                                </li>
                            </ul>
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

                            <h2 class="page-header">
                                Contato <small>Professores</small>
                            </h2>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="fa fa-book"></i> Contato
                                </li>
                            </ol>

                            <div id="tabela"style="margin-left: 170px; margin-top: 20px;">
                                <table style="margin-top: 10px; ">
                                    <tr>
                                        <td  style="width: 380px; ">
                                            <img src="imagens/cris01.jpeg"/>
                                        </td>

                                        <td  style="margin-left:50px;">
                                            <img src="imagens/rdms01.jpeg"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <br/>
                                    <center><b>cristhalmeman@gmail.com</b></center>
                                    </td>

                                    <td>
                                        <br/>
                                    <center><b>radames@utfpr.edu.br</b></center>
                                    </td>

                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                    <center><b><a href="ContatoServlet?op=cris">Clique para mais informações</a></b></center>
                                    </td>
                                    <td>
                                        <br/>
                                    <center><b><a href="ContatoServlet?op=radames">Clique para mais informações</a></b></center>
                                    </td>
                                    </tr>
                                </table>
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
