
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <!--        <form action="ServletCadastro" method="POST">-->

        <div id="form-cadastro">  
            <h1>Cadastro</h1>   
            <form action="LoginServlet" method="POST">
                <br/>Nome Completo:
                <input type="text" name="nome_usuario" value="" />
                
                <br/> Email:
                <input type="email" name="email_usuario" value="" />
                
                <br/>Senha:
                <input type="password" name="senha_usuario" value=""/>

                <br/><input type="submit" value="Cadastro" name="op" style="width: 100px;"/>

            </form>
        </div>

    </body>
</html>
