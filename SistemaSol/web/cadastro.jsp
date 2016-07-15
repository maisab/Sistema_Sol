<%-- 
    Document   : cadastro
    Created on : 07/07/2016, 21:01:23
    Author     : maa
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="css/cadastro.css" rel="stylesheet">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
    </head> 
    <body>
        <a href="login.jsp"><div class="logo"> </div></a>
        <div class="cadastro-block">
            <form action="CadastroServlet" id="formCadastro" method="POST">
                <h3 style="color:red;">${erroCadastro}</h3>
                <h1>Cadastro</h1>
                <input type="text" value="" name="nome_usuario" placeholder="Nome Completo" id="username" required/>
                <input type="password" value="" name="senha_usuario" placeholder="Senha" id="password" onchange="formCadastro.passwordC.pattern = this.value;" required/>
                <input type="password" value="" placeholder="Confirmar Senha" id="passwordC"onchange="this.setCustomValidity(this.validity.patternMismatch ? 'As senhas não conferem' : '')" required/>
                <input type="email" value="" name="email_usuario" placeholder="Email" id="useremail" required/>
                <input type="submit" value="Cadastro" name="op"/>
            </form>
        </div>
    </body>
</body>
</html>
