<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Livros</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header>
    <div class="container">
        <h1>Sistema de Livros</h1>
        <p>Trabalho de Java Web - 3 Semestre</p>
    </div>
</header>

<main class="container">
    <div class="header-actions">
        <h2>Livros no Sistema</h2>
        <a href="${pageContext.request.contextPath}/livros/novo" class="btn btn-primary">Cadastrar Novo</a>
    </div>

    <div class="card">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>ISBN</th>
                <th>Autor</th>
                <th>Data</th>
                <th>Preço</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="l" items="${livros}">
                <tr>
                    <td>${l.id}</td>
                    <td>${l.nomeLivro}</td>
                    <td>${l.isbn}</td>
                    <td>${l.autor}</td>
                    <td>${l.dataFormatada}</td>
                    <td>${l.precoFormatado}</td>
                    <td class="actions">
                        <a href="${pageContext.request.contextPath}/livros/editar?id=${l.id}" class="btn btn-primary" style="padding: 5px 10px;">Editar</a>
                        <a href="${pageContext.request.contextPath}/livros/excluir?id=${l.id}" class="btn btn-danger" style="padding: 5px 10px;" onclick="return confirm('Apagar este livro?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty livros}">
                <tr>
                    <td colspan="7" style="text-align: center; padding: 20px;">Nenhum livro cadastrado.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>

<footer>
    <div class="container">
        <p>Desenvolvido por: Giovani Nunes Marques Luiz e Adrienne Xavier de Carvalho</p>
    </div>
</footer>

</body>
</html>
