/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Movimentacao;
import model.Produto;
import model.TipoMovimentacao;
import model.dao.InterfaceDao;
import model.dao.MovimentacaoDao;
import model.dao.ProdutoDao;

/**
 *
 * @author Pedro
 */
@WebServlet("/movimentacao")
public class MovimentacaoSrv extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {

            String acao = request.getParameter("acao");

            if (acao == null) {
                acao = "listagem";
            }

            String id = request.getParameter("id");
            String qtd = request.getParameter("qtd");
            String data = request.getParameter("data");
            String observacao = request.getParameter("observacao");
            String produto = request.getParameter("produtoMovimento");
            String tipo = request.getParameter("tipo");

            InterfaceDao dao = new MovimentacaoDao();
            RequestDispatcher rd;

            Movimentacao m1 = null;
            Produto p1 = null;
            TipoMovimentacao tipoMov;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataMov;

            switch (acao) {

case "inclusao":
    dataMov = sdf.parse(data);

    m1 = new Movimentacao();
    m1.setQtd(Integer.parseInt(qtd));
    m1.setObservacao(observacao);
    m1.setData(dataMov);

    tipoMov = TipoMovimentacao.valueOf(tipo);
    m1.setTipo(tipoMov);

    model.dao.ProdutoDao pDao = new model.dao.ProdutoDao();
    p1 = pDao.pesquisarPorId(Integer.parseInt(produto));

    if (tipoMov == TipoMovimentacao.SAIDA && p1.getEstoque() < Integer.parseInt(qtd)) {
        request.setAttribute("erro", "Estoque insuficiente! Saldo atual: " + p1.getEstoque());
        request.setAttribute("listagem", dao.listar());
        request.setAttribute("produtosDisponiveis", pDao.listar());
        rd = request.getRequestDispatcher("movimentacao.jsp");
        rd.forward(request, response);
        break;
    }

    if (tipoMov == TipoMovimentacao.ENTRADA) {
        p1.setEstoque(p1.getEstoque() + m1.getQtd());
    } else {
        p1.setEstoque(p1.getEstoque() - m1.getQtd());
    }

    m1.setSaldoMomento(p1.getEstoque());
    m1.setProdutoMovimento(p1);

    dao.incluir(m1);
    pDao.editar(p1);

    rd = request.getRequestDispatcher("movimentacao?acao=listagem");
    rd.forward(request, response);
    break;

                case "pre-edicao":
                    String idPre = request.getParameter("id");
                    Movimentacao mPre = (Movimentacao) dao.pesquisarPorId(Integer.parseInt(idPre));
                    request.setAttribute("movEditando", mPre);

                    request.setAttribute("produtosDisponiveis", new ProdutoDao().listar());

                    rd = request.getRequestDispatcher("movimentacao.jsp");
                    rd.forward(request, response);
                    break;

               case "edicao":
    dataMov = sdf.parse(data);

    Movimentacao movAntiga = (Movimentacao) dao.pesquisarPorId(Integer.parseInt(id));
    int qtdAntiga = movAntiga.getQtd();
    TipoMovimentacao tipoAntigo = movAntiga.getTipo();

    model.dao.ProdutoDao pDaoEd = new model.dao.ProdutoDao();
    p1 = pDaoEd.pesquisarPorId(Integer.parseInt(produto));

    // 1) Reverte o efeito da movimentação antiga
    if (tipoAntigo == TipoMovimentacao.ENTRADA) {
        p1.setEstoque(p1.getEstoque() - qtdAntiga);
    } else {
        p1.setEstoque(p1.getEstoque() + qtdAntiga);
    }

    int qtdNova = Integer.parseInt(qtd);
    TipoMovimentacao tipoNovo = TipoMovimentacao.valueOf(tipo);

    // ✅ 2) Verifica ANTES de aplicar o novo
    if (tipoNovo == TipoMovimentacao.SAIDA && p1.getEstoque() < qtdNova) {
        request.setAttribute("erro", "Estoque insuficiente! Saldo atual: " + p1.getEstoque());
        request.setAttribute("listagem", dao.listar());
        request.setAttribute("produtosDisponiveis", pDaoEd.listar());
        rd = request.getRequestDispatcher("movimentacao.jsp");
        rd.forward(request, response);
        break;
    }

    // 3) Aplica o novo tipo
    if (tipoNovo == TipoMovimentacao.ENTRADA) {
        p1.setEstoque(p1.getEstoque() + qtdNova);
    } else {
        p1.setEstoque(p1.getEstoque() - qtdNova);
    }

    movAntiga.setQtd(qtdNova);
    movAntiga.setTipo(tipoNovo);
    movAntiga.setData(dataMov);
    movAntiga.setObservacao(observacao);
    movAntiga.setProdutoMovimento(p1);
    movAntiga.setSaldoMomento(p1.getEstoque());

    dao.editar(movAntiga);
    pDaoEd.editar(p1);

    request.setAttribute("listagem", dao.listar());
    request.setAttribute("produtosDisponiveis", pDaoEd.listar());
    rd = request.getRequestDispatcher("movimentacao.jsp");
    rd.forward(request, response);
    break;

                case "exclusao":
                    m1 = (Movimentacao) dao.pesquisarPorId(Integer.parseInt(id));
                    dao.excluir(m1);

                    request.setAttribute("listagem", dao.listar());
                    request.setAttribute("produtosDisponiveis", new ProdutoDao().listar());

                    rd = request.getRequestDispatcher("movimentacao.jsp");
                    rd.forward(request, response);
                    break;

                case "listagem":

                    List<Movimentacao> lista = dao.listar();
                    request.setAttribute("listagem", lista);

                    List<Produto> listaProdutos = new ProdutoDao().listar();
                    request.setAttribute("produtosDisponiveis", listaProdutos);

                    rd = request.getRequestDispatcher("movimentacao.jsp");
                    rd.forward(request, response);

                    break;

                case "pesquisa":
                    String buscaMov = request.getParameter("busca");

                    model.dao.MovimentacaoDao mDaoBusca = new model.dao.MovimentacaoDao();
List<Movimentacao> resultado = mDaoBusca.pesquisarPorObservacao(buscaMov);

                    request.setAttribute("listagem", resultado);

                    request.setAttribute("produtosDisponiveis", new model.dao.ProdutoDao().listar());

                    rd = request.getRequestDispatcher("movimentacao.jsp");
                    rd.forward(request, response);
                    break;

                default:
                    System.out.println("Erro na variável acao: " + acao);
                    break;
            }

        } catch (Exception e) {
            System.out.println("Erro ===> " + e.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
