<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Formulario de Livro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header>
    <div class="container">
        <h1>Sistema de Livros</h1>
        <p>Cadastro e Edição</p>
    </div>
</header>

<main class="container">
    <div class="header-actions">
        <h2>${livro == null ? 'Novo Livro' : 'Editar Livro'}</h2>
        <a href="${pageContext.request.contextPath}/livros" class="btn btn-danger">Cancelar</a>
    </div>

    <div class="card" style="max-width: 500px; margin: 0 auto;">
        <form action="${pageContext.request.contextPath}/livros/salvar" method="post">
            <c:if test="${livro != null}">
                <input type="hidden" name="id" value="${livro.id}">
            </c:if>

            <div class="form-group">
                <label>Nome do Livro</label>
                <input type="text" name="nomeLivro" value="${livro.nomeLivro}" required>
            </div>

            <div class="form-group">
                <label>ISBN</label>
                <input type="text" name="isbn" value="${livro.isbn}" required>
            </div>

            <div class="form-group">
                <label>Autor</label>
                <input type="text" name="autor" value="${livro.autor}" required>
            </div>

            <div class="grid-2">
                <div class="form-group">
                    <label>Data de Publicação</label>
                    <input type="date" name="dataPublicacao" value="${livro.dataPublicacao}" required>
                </div>

                <div class="form-group">
                    <label>Preço</label>
                    <input type="text" name="valorLivro" value="${livro.valorLivro}" required>
                </div>
            </div>

            <div style="margin-top: 20px;">
                <button type="submit" class="btn btn-primary" style="width: 100%;">Salvar</button>
            </div>
        </form>
    </div>
</main>

<footer>
    <div class="container">
        <p>Desenvolvido por: Giovani Nunes Marques Luiz e Adrienne Xavier de Carvalho</p>
    </div>
</footer>

</body>
</html>
