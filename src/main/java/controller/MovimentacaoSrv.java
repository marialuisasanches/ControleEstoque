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

                    p1 = new Produto();
                    p1.setId(Integer.parseInt(produto));
                    m1.setProdutoMovimento(p1);

                    dao.incluir(m1);

                    rd = request.getRequestDispatcher("index.html");
                    rd.forward(request, response);

                    break;

                case "edicao":
                    dataMov = sdf.parse(data);

                    m1 = new Movimentacao();

                    m1.setId(Integer.parseInt(id));
                    m1.setQtd(Integer.parseInt(qtd));
                    m1.setObservacao(observacao);
                    m1.setData(dataMov);

                    tipoMov = TipoMovimentacao.valueOf(tipo);
                    m1.setTipo(tipoMov);

                    p1 = new Produto();
                    p1.setId(Integer.parseInt(produto));

                    m1.setProdutoMovimento(p1);

                    dao.editar(m1);

                    List<Movimentacao> listaEdit = dao.listar();
                    request.setAttribute("listagem", listaEdit);

                    rd = request.getRequestDispatcher("movimentacao.jsp");
                    rd.forward(request, response);

                    break;

                case "exclusao":

                    m1 = new Movimentacao();
                    m1.setId(Integer.parseInt(id));

                    dao.excluir(m1);

                    List<Movimentacao> listaExc = dao.listar();
                    request.setAttribute("listagem", listaExc);

                    rd = request.getRequestDispatcher("movimentacao.jsp");
                    rd.forward(request, response);

                    break;

                case "listagem":

                    List<Movimentacao> lista = dao.listar();
                    request.setAttribute("listagem", lista);

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
