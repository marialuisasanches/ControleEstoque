<%-- 
    Document   : empresa
    Created on : 14 de mar. de 2026, 17:34:37
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <%
        String acao = request.getParameter("acao");
    %> 

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background-color: #dfd9e4;
            font-family: "Roboto", sans-serif;
            background-image: url(./images/fundo.png);
        }

        header {
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #260940;
        }

        img {
            width: 150px;
            height: auto;
            padding: 1rem;
            filter: drop-shadow(5px 5px 10px rgba(0, 0, 0, 0.8));
        }

        main {
            color: #dfd9e4;
            min-height: 75vh;
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            gap: 6rem;
        }

        .container {
            display: flex;
        }

        .sidebar {
            width: 200px;
            height: 77vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            gap: 3rem;
            padding: 20px;
            background-color: #260940;
        }

        .sidebar p {
            cursor: pointer;
            transition: transform 0.3s ease;
            width: 200px;
            text-align: center;
            padding: 1rem;
            border-radius: 5px;
        }

        .sidebar p:last-child:hover {
            background-color: #ac0d0d;
            border: 1px solid #ac0d0d;
            transform: scale(1.1);
            width: 200px;
            text-align: center;
        }

        .sidebar p:hover {
            background-color: #5d00ae;
            border: 1px solid #5d00ae;
            transform: scale(1.1);
            width: 200px;
            text-align: center;
        }

        .meio {
            display: flex;
            align-items: center;
            width: 100%;
        }
        table {
            border-collapse: collapse;
            width: 50%;
            margin: 2rem auto;
            background-color: rgba(38, 9, 64, 0.6);
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.4);
            align-self: flex-start;
            text-align: center;
        }

        th {
            background-color: #5d00ae;
            color: #dfd9e4;
            padding: 12px 20px;
            text-align: left;
            font-weight: 600;
            letter-spacing: 0.05em;
            text-transform: uppercase;
            font-size: 0.85rem;
            text-align: center;
        }

        td {
            padding: 12px 20px;
            color: #dfd9e4;
            border-bottom: 1px solid rgba(93, 0, 174, 0.3);
        }

        tr:last-child td {
            border-bottom: none;
        }

        tr:hover td {
            background-color: rgba(93, 0, 174, 0.25);
            transition: background-color 0.2s ease;
        }

        footer {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            background-color: #260940;
            color: #e4e1d9;
            padding: 1rem;
        }
    </style>

    <body>
        <header>
            <img src="./images/ControleDeEstoqueIMG.png" />
        </header>
        <main>
            <div class="meio">
                <form action="empresa" method="GET" style="margin: 2rem auto 0 auto; background: rgba(38, 9, 64, 0.6); padding: 15px; border-radius: 10px; width: 50%; display: flex; gap: 10px; align-items: center; justify-content: center;">
                    <input type="hidden" name="acao" value="pesquisa">
                    <label>Buscar Empresa:</label>
                    <input type="text" name="busca" placeholder="Digite o nome..." style="padding: 5px; border-radius: 4px; border: none; width: 200px;">
                    <button type="submit" style="background-color: #5d00ae; color: white; border: none; padding: 5px 15px; border-radius: 4px; cursor: pointer;">Buscar</button>
                    <a href="empresa?acao=listagem" style="color: #dfd9e4; text-decoration: none; font-size: 0.8rem;">Limpar</a>
                </form>
                <section class="container">

                    <nav class="sidebar">
                        <a href="index.html">Inicio</a>
                        <form action="empresa" method="POST">
                            <input type="hidden" name="acao" value="${empty empresaEditando ? 'inclusao' : 'edicao'}">

                            <c:if test="${not empty empresaEditando}">
                                <input type="hidden" name="id" value="${empresaEditando.id}">
                            </c:if>

                            <label>Nome da Empresa:</label><br>
                            <input type="text" name="nome" value="${empresaEditando.nome}" required><br><br>

                            <label>CNPJ:</label><br>
                            <input type="text" name="cnpj" value="${empresaEditando.cnpj}" required><br><br>

                            <label>Telefone:</label><br>
                            <input type="text" name="telefone" value="${empresaEditando.telefone}" required><br><br>

                            <button type="submit">
                                ${empty empresaEditando ? 'Cadastrar Empresa' : 'Salvar Alterações'}
                            </button>

                            <c:if test="${not empty empresaEditando}">
                                <br><br>
                                <a href="empresa?acao=listagem" style="color: #ff5252; text-decoration: none; font-size: 0.8rem;">Cancelar Edição</a>
                            </c:if>
                        </form>
                    </nav>

                </section>

                <table border="1">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>CNPJ</th>
                            <th>Telefone</th>
                            <th colspan="2">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="empresa" items="${listagem}">
                            <tr>
                                <td>${empresa.nome}</td>
                                <td>${empresa.cnpj}</td>
                                <td>${empresa.telefone}</td>
                                <td>
                                    <a href="empresa?acao=pre-edicao&id=${empresa.id}" style="color: #00bcd4; text-decoration: none; font-weight: bold;">Editar</a>
                                </td>
                                <td>
                                    <a href="empresa?acao=exclusao&id=${empresa.id}" 
                                       style="color: #ff5252; text-decoration: none; font-weight: bold;"
                                       onclick="return confirm('Deseja realmente excluir a empresa ${empresa.nome}?')">
                                        Excluir
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </main>

        <footer>
            <p>&copy; 2026</p>
        </footer>
    </body>
</html>
