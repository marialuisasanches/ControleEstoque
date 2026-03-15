<%-- 
    Document   : movimentacao
    Created on : 14 de mar. de 2026, 17:34:59
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
            <img src= "./images/ControleDeEstoqueIMG.png" />
        </header>
        <main>
            <div class="meio">

                <section class="container">
                    <nav class="sidebar">
                        <form action="movimentacao" method="POST">
                            <input type="hidden" name="acao" value="inclusao">

                            <label>Produto (ID):</label>
                            <input type="number" name="produtoMovimento" required placeholder="ID do Produto">

                            <label>Quantidade:</label>
                            <input type="number" name="qtd" required>

                            <label>Tipo:</label>
                            <select name="tipo">
                                <option value="ENTRADA">Entrada</option>
                                <option value="SAIDA">Saída</option>
                            </select>

                            <label>Data:</label>
                            <input type="date" name="data" required>

                            <label>Observação:</label>
                            <textarea name="observacao"></textarea>

                            <button type="submit">Registrar</button>
                        </form>
                    </nav>
                </section>

                <table border="1">
                    <tr>
                        <th>Empresa</th>
                        <th>Produto</th>
                        <th>Tipo</th>
                        <th>Quantidade</th>
                        <th>Data</th>
                    </tr>

                    <c:forEach var="mov" items="${listagem}">
                        <tr>
                            <td>${mov.produtoMovimento.empresa.nome}</td>
                            <td>${mov.produtoMovimento.nome}</td>
                            <td>${mov.tipo}</td>
                            <td>${mov.qtd}</td>
                            <td>${mov.data}</td>
                        </tr>
                    </c:forEach>

                </table>

            </div>
        </main>

        <footer>
            <p>&copy; 2026</p>
        </footer>
    </body>
</html>
