<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Adicionar Pessoa</h1>
        
        <form method="post" action="${linkTo[PessoaController].salva}">
            
            <label>Nome: </label>
            <input name="pessoa.nome">
            
            <label>Endereço: </label>
            <input name="pessoa.endereco.endereco">
            
            <button type="submit">Salvar</button>
            
        </form>
    </body>
</html>
