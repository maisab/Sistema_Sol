
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="css/login.css" rel="stylesheet">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
    </head> 

    <body>
        <div class="logo"></div>
        <div class="login-block">
            <form action="LoginServlet" method="POST">
                <h1>Login</h1>
                <input type="text" value="" name="username" placeholder="Username" id="username" />
                <input type="password" name="userpass" value="" placeholder="Password" id="password" />
                <input type="submit" value="Login" name="op" />
            </form>
        </div>
    </body>

    <!--                <div id="form-login">  
                        <h1>Seja Bem-vindo</h1>   
                        <form action="LoginServlet" method="POST">
                            <br/>Login:
                            <input type="text" name="username" value="" />
                            <br/>Senha:
                            <input type="password" name="userpass" value=""/>
                            
                            <br/><input type="submit" value="Login" name="op" style="width: 100px;"/>
                            
                            <br/><br/>Não possui cadastro? <a href="LoginServlet?op=cad">Cadastre-se</a>
                        </form>
                    </div>-->



</body>
</html>
