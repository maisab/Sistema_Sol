
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <link href="css/cadastro.css" rel="stylesheet">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro</title>

    </head> 
    <body>
        <a href="login.jsp"><div class="logo"> </div></a>
        <div class="cadastro-block">
            <form action="LoginServlet" id="formCadastro" method="POST">
                <h1>Cadastro</h1>
                <input type="text" value="" name="nome_usuario" placeholder="Nome Completo" id="username" required/>
                <input type="password" value="" placeholder="Senha" id="password" onchange="formCadastro.passwordC.pattern = this.value;" required/>
                <input type="password" value="" placeholder="Confirmar Senha" id="passwordC"onchange="this.setCustomValidity(this.validity.patternMismatch ? 'As senhas não conferem' : '')" required/>
                <input type="email" value="" name="email_usuario" placeholder="Email" id="useremail" required/>
                <input type="submit" value="Cadastro" name="op"/>
            </form>

        </div>

    </body>
    <!--    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>-->

    <!--        <form action="ServletCadastro" method="POST">-->

    <!--        <div id="form-cadastro">  
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
            </div>-->

</body>
</html>
