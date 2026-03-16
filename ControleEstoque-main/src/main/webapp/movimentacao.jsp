<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Controle de Estoque - Movimentação</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600;700&display=swap" rel="stylesheet">
    </head>

    <%
        String acao = request.getParameter("acao");
    %>

    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }

        body {
            font-family: "Roboto", sans-serif;
            background-color: #dfd9e4;
            background-image: url(./images/fundo.png);
            background-size: cover;
            background-attachment: fixed;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        header {
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #260940;
            padding: 0.5rem 0;
        }
        header img {
            width: 120px;
            height: auto;
            filter: drop-shadow(3px 3px 8px rgba(0,0,0,0.8));
        }

        main {
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 2rem 1rem;
            gap: 1.5rem;
        }

        .alerta-erro {
            width: 100%;
            max-width: 1200px;
            background-color: rgba(172, 13, 13, 0.85);
            color: #fff;
            padding: 12px 20px;
            border-radius: 8px;
            font-weight: 600;
            font-size: 0.95rem;
            border-left: 5px solid #ff1744;
        }

        .busca-bar {
            width: 100%;
            max-width: 700px;
            background: rgba(38, 9, 64, 0.75);
            padding: 14px 20px;
            border-radius: 10px;
            display: flex;
            gap: 10px;
            align-items: center;
            justify-content: center;
            flex-wrap: wrap;
        }
        .busca-bar label {
            color: #dfd9e4;
            font-weight: 600;
            font-size: 0.9rem;
            white-space: nowrap;
        }
        .busca-bar input[type="text"] {
            padding: 7px 12px;
            border-radius: 6px;
            border: none;
            outline: none;
            width: 220px;
            font-size: 0.9rem;
        }
        .busca-bar button {
            background-color: #5d00ae;
            color: white;
            border: none;
            padding: 7px 18px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 600;
            transition: background 0.2s;
        }
        .busca-bar button:hover { background-color: #7b00e0; }
        .busca-bar a {
            color: #dfd9e4;
            text-decoration: none;
            font-size: 0.85rem;
            opacity: 0.8;
        }
        .busca-bar a:hover { opacity: 1; text-decoration: underline; }

        .layout {
            width: 100%;
            max-width: 1200px;
            display: flex;
            gap: 1.5rem;
            align-items: flex-start;
        }

        .form-card {
            background: rgba(38, 9, 64, 0.85);
            border-radius: 12px;
            padding: 1.5rem;
            width: 260px;
            min-width: 240px;
            display: flex;
            flex-direction: column;
            gap: 0.4rem;
            box-shadow: 0 4px 20px rgba(0,0,0,0.4);
        }
        .form-card > a {
            color: #dfd9e4;
            text-decoration: none;
            font-size: 0.85rem;
            text-align: center;
            opacity: 0.8;
            margin-bottom: 0.5rem;
            display: block;
        }
        .form-card > a:hover { opacity: 1; text-decoration: underline; }
        .form-card label {
            color: #dfd9e4;
            font-size: 0.85rem;
            font-weight: 600;
            display: block;
            margin-top: 0.4rem;
            margin-bottom: 2px;
        }
        .form-card input[type="number"],
        .form-card input[type="date"],
        .form-card select,
        .form-card textarea {
            width: 100%;
            padding: 8px 10px;
            border-radius: 6px;
            border: none;
            outline: none;
            font-size: 0.9rem;
            font-family: "Roboto", sans-serif;
        }
        .form-card textarea {
            resize: vertical;
            min-height: 70px;
        }
        .form-card button[type="submit"] {
            width: 100%;
            padding: 9px;
            background-color: #5d00ae;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 600;
            font-size: 0.95rem;
            transition: background 0.2s;
            margin-top: 0.6rem;
        }
        .form-card button[type="submit"]:hover { background-color: #7b00e0; }
        .cancelar-link {
            display: block;
            text-align: center;
            color: #ff5252;
            margin-top: 0.6rem;
            font-size: 0.85rem;
            text-decoration: none;
        }
        .cancelar-link:hover { text-decoration: underline; }

        .tabela-wrapper { flex: 1; }

        table {
            border-collapse: collapse;
            width: 100%;
            background-color: rgba(38, 9, 64, 0.7);
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0,0,0,0.4);
        }
        th {
            background-color: #5d00ae;
            color: #dfd9e4;
            padding: 13px 12px;
            font-weight: 700;
            letter-spacing: 0.04em;
            text-transform: uppercase;
            font-size: 0.78rem;
            text-align: center;
        }
        td {
            padding: 11px 12px;
            color: #dfd9e4;
            border-bottom: 1px solid rgba(93, 0, 174, 0.3);
            text-align: center;
            font-size: 0.88rem;
        }
        tr:last-child td { border-bottom: none; }
        tr:hover td {
            background-color: rgba(93, 0, 174, 0.25);
            transition: background-color 0.2s ease;
        }
        td a { text-decoration: none; font-weight: 600; font-size: 0.85rem; }

        .nao-encontrado {
            color: #dfd9e4;
            font-style: italic;
            opacity: 0.7;
            padding: 20px;
        }

        .badge-entrada {
            background-color: rgba(0, 180, 100, 0.25);
            color: #00e676;
            padding: 3px 10px;
            border-radius: 20px;
            font-size: 0.8rem;
            font-weight: 600;
        }
        .badge-saida {
            background-color: rgba(255, 82, 82, 0.2);
            color: #ff5252;
            padding: 3px 10px;
            border-radius: 20px;
            font-size: 0.8rem;
            font-weight: 600;
        }

        footer {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            background-color: #260940;
            color: #e4e1d9;
            padding: 1rem;
            font-size: 0.85rem;
        }
    </style>

    <body>
        <header>
            <img src="./images/ControleDeEstoqueIMG.png" />
        </header>

        <main>

            <%-- ALERTA DE ERRO DE ESTOQUE --%>
            <c:if test="${not empty erro}">
                <div class="alerta-erro">&#9888; ${erro}</div>
            </c:if>

            <!-- BUSCA -->
            <form action="movimentacao" method="GET" class="busca-bar">
                <input type="hidden" name="acao" value="pesquisa">
                <label>Filtrar por Empresa:</label>
                <input type="text" name="busca" placeholder="Nome da empresa...">
                <button type="submit">Filtrar</button>
                <a href="movimentacao?acao=listagem">Limpar</a>
            </form>

            <!-- LAYOUT: FORM + TABELA -->
            <div class="layout">

                <!-- FORMULÁRIO -->
                <div class="form-card">
                    <a href="index.html">&#8592; In&#237;cio</a>
                    <form action="movimentacao" method="POST">
                        <input type="hidden" name="acao" value="${movEditando != null ? 'edicao' : 'inclusao'}">
                        <c:if test="${movEditando != null}">
                            <input type="hidden" name="id" value="${movEditando.id}">
                        </c:if>

                        <label>Produto:</label>
                        <select name="produtoMovimento" required>
                            <option value="">Selecione...</option>
                            <c:forEach var="p" items="${produtosDisponiveis}">
                                <option value="${p.id}" ${movEditando.produtoMovimento.id == p.id ? 'selected' : ''}>
                                    ${p.nome} (Saldo: ${p.estoque})
                                </option>
                            </c:forEach>
                        </select>

                        <label>Quantidade:</label>
                        <input type="number" name="qtd" min="1" value="${movEditando.qtd}" required>

                        <label>Tipo:</label>
                        <select name="tipo">
                            <option value="ENTRADA" ${movEditando.tipo == 'ENTRADA' ? 'selected' : ''}>Entrada</option>
                            <option value="SAIDA" ${movEditando.tipo == 'SAIDA' ? 'selected' : ''}>Sa&#237;da</option>
                        </select>

                        <label>Data:</label>
                        <input type="date" name="data" value="${movEditando.data}" required>

                        <label>Observa&#231;&#227;o:</label>
                        <textarea name="observacao">${movEditando.observacao}</textarea>

                        <button type="submit">${movEditando != null ? 'Salvar Altera&#231;&#227;o' : 'Registrar'}</button>

                        <c:if test="${movEditando != null}">
                            <a href="movimentacao?acao=listagem" class="cancelar-link">Cancelar</a>
                        </c:if>
                    </form>
                </div>

                <!-- TABELA -->
                <div class="tabela-wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>Empresa</th>
                                <th>Produto</th>
                                <th>Tipo</th>
                                <th>Quantidade</th>
                                <th>Data</th>
                                <th>Saldo Atual</th>
                                <th>A&#231;&#245;es</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty listagem}">
                                    <tr>
                                        <td colspan="7" class="nao-encontrado">Nenhuma movimenta&#231;&#227;o encontrada.</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="mov" items="${listagem}">
                                        <tr>
                                            <td>${mov.produtoMovimento.empresa.nome}</td>
                                            <td>${mov.produtoMovimento.nome}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${mov.tipo == 'ENTRADA'}">
                                                        <span class="badge-entrada">ENTRADA</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge-saida">SA&#205;DA</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${mov.qtd}</td>
                                            <td>${mov.data}</td>
                                            <td>${mov.saldoMomento}</td>
                                            <td>
                                                <a href="movimentacao?acao=pre-edicao&id=${mov.id}" style="color:#00bcd4;">Editar</a>
                                                &nbsp;|&nbsp;
                                                <a href="movimentacao?acao=exclusao&id=${mov.id}"
                                                   style="color:#ff5252;"
                                                   onclick="return confirm('Deseja excluir esta movimenta&#231;&#227;o?')">Excluir</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>

            </div>
        </main>

        <footer>
            <p>&copy; 2026 &mdash; Feito para fins Educacionais</p>
        </footer>
    </body>
</html>
