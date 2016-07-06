
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>

        <div id="form-login">  
            <h1>Seja Bem-vindo</h1>   
            <form action="LoginServlet" method="POST">
                <br/>Login:
                <input type="text" name="username" value="" />
                <br/>Senha:
                <input type="password" name="userpass" value=""/>
                
                <br/><input type="submit" value="Login" name="op" style="width: 100px;"/>
                
                <br/><br/>Não possui cadastro? <a href="LoginServlet?op=cad">Cadastre-se</a>
            </form>
        </div>


        <!--
        <form action="LoginServlet" method="POST">
            <fieldset style="width: 300px">
                <table>
                    <tr>
                        <td> User ID </td>
                        <td><input type="text" name="username"/></td>
                    </tr>
                    <tr>
                        <td>Password </td>
                        <td><input type="password" name="userpass"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Login"/></td>
                    </tr>

                </table>

            </fieldset>

        </form>
        -->
    </body>
</html>
