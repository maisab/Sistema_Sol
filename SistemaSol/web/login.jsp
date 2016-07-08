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
                <h1>Login</h1>
                <input type="email" value="" name="username" placeholder="Email" id="username" required/>
                <input type="password" name="userpass" value="" placeholder="Senha" id="password" required/>
                <input type="submit" value="Login" name="op" />
            </form>            
            <%
//                if (session != null) {
//                    Boolean cadastrado = (Boolean) session.getAttribute("Cadastrado");
//                    if (cadastrado) {
//                        out.println("Usuário cadastrado com sucesso!");
//                    } else {
//                        out.println("Não foi possivel realizar o cadastro");
//                    }
//                }
            %>
            <br/><br/>Não possui cadastro? <a href="LoginServlet?op=cad">Cadastre-se</a>
        </div>
    </body>
</html>
