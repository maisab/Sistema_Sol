<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="css/login.css" rel="stylesheet">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head> 
    <body>
        <div class="logo"></div>

        <div class="login-block">
            <form action="LoginServlet" method="POST">
                <div class="alert alert-success">
                    <h3 style="color:orange;">${mensagemCadastrado}</h3>
                    <h3 style="color:red;">${erroLogin}</h3>
                </div>
                <h1>Login</h1>
                <input type="email" value="" name="username" placeholder="Email" id="username" required/>
                <input type="password" name="userpass" value="" placeholder="Senha" id="password" required/>
                <input type="submit" value="Login" name="op"/>
            </form>            
            <h3>Não possui cadastro? <a href="LoginServlet?op=cad">Cadastre-se</a></h3>

            <p><p>
        </div>
    </body>
</html>
