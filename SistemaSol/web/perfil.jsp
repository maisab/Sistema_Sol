<%@page import="model.Usuario"%>
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

        <title>Perfil do Usuário</title>
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
                    <a class="navbar-brand" href="#">Perfil do usuário</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%=session.getAttribute("nome_usuario")%> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="active">
                                <a href="#"><i class="fa fa-fw fa-user"></i>Perfil</a>
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
                        <li>
                            <a href="#"><i class="fa fa-fw fa-info"></i> Sobre o projeto</a>
                        </li>
                        <li>
                            <a href="LoginServlet?op=contato"><i class="fa fa-fw fa-book"></i> Contato</a>
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
                                Perfil <small>Usuário</small>
                            </h2>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="fa fa-user"></i> Perfil
                                </li>
                            </ol>
                            <div class="perfil-block">

                                <%
                                    String nome = (String) session.getAttribute("nome_usuario");
                                    String senha = (String) session.getAttribute("senha_usuario");
                                    String email = (String) session.getAttribute("email_usuario");
                                    if (!(Boolean) session.getAttribute("editarPerfil")) {

                                %>
                                <form>
                                    <b>Nome: </b> 
                                    <b style="color: red">*</b>
                                    <br/> 
                                    <%                                        
                                        out.println("<input type=\"text\" name=\"nome_usuario\" value=\"" + nome + "\" disabled/>");
                                    %> 

                                    <b>Senha: </b>
                                    <b style="color: red">*</b>
                                    <br/> 
                                    <%
                                        out.println("<input type=\"password\" name=\"senha_usuario\" value=\"" + senha + "\" disabled/>");
                                    %> 

                                    <b>Email: </b>
                                    <b style="color: red">*</b>
                                    <br/> 
                                    <%
                                        out.println("<input type=\"email\" name=\"email_usuario\" value=\"" + email + "\" disabled/>");
                                    %>
                                    <h3><a href="PerfilServlet?op=editarP">Editar Perfil</a></h3>
                                </form>

                                <% } else {%>
                                <form action="PerfilServlet" id="formCadastro" method="POST">
                                    <b>Nome: </b> 
                                    <b style="color: red">*</b>
                                    <br/> 
                                    <%
                                        out.println("<input type=\"text\" name=\"nome_usuario\" value=\"" + nome + "\"/>");
                                    %> 

                                    <b>Senha: </b> 
                                    <b style="color: red">*</b>
                                    <br/> 
                                    <%
                                        out.println("<input type=\"text\" name=\"senha_usuario\"  id=\"senha\" value=\"" + senha + "\"/>");
                                    %> 

                                    <b>Email: </b> 
                                    <b style="color: red">*</b>
                                    <br/> 
                                    <%
                                        out.println("<input type=\"email\" name=\"email_usuario\" value=\"" + email + "\" readonly/>");
                                    %>
                                    <input type="submit" value="Editar Perfil" name="op" />
                                </form>

                                <%}%>
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
