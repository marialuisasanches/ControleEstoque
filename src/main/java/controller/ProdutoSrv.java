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
import java.util.List;
import model.Empresa;
import model.Produto;
import model.dao.InterfaceDao;
import model.dao.ProdutoDao;

/**
 *
 * @author Pedro
 */
@WebServlet("/produto")
public class ProdutoSrv extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {

            String acao = request.getParameter("acao");

            if (acao == null) {
                acao = "listagem";
            }

            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String preco = request.getParameter("preco");
            String estoque = request.getParameter("estoque");
            String empresa = request.getParameter("empresa");

            InterfaceDao dao = new ProdutoDao();
            RequestDispatcher rd;

            Produto p1 = null;
            Empresa emp = null;

            switch (acao) {

                case "inclusao":

                    p1 = new Produto();
                    p1.setNome(nome);
                    p1.setPreco(Double.parseDouble(preco));
                    p1.setEstoque(Integer.parseInt(estoque));

                    emp = new Empresa();
                    emp.setId(Integer.parseInt(empresa));

                    p1.setEmpresa(emp);

                    dao.incluir(p1);

                    rd = request.getRequestDispatcher("index.html");
                    rd.forward(request, response);

                    break;

                case "edicao":

                    p1 = new Produto();

                    p1.setId(Integer.parseInt(id));
                    p1.setNome(nome);
                    p1.setPreco(Double.parseDouble(preco));
                    p1.setEstoque(Integer.parseInt(estoque));

                    emp = new Empresa();
                    emp.setId(Integer.parseInt(empresa));

                    p1.setEmpresa(emp);

                    dao.editar(p1);

                    List<Produto> listaEdit = dao.listar();
                    request.setAttribute("listagem", listaEdit);

                    rd = request.getRequestDispatcher("produto.jsp");
                    rd.forward(request, response);

                    break;

                case "exclusao":

                    p1 = new Produto();
                    p1.setId(Integer.parseInt(id));

                    dao.excluir(p1);

                    List<Produto> listaExc = dao.listar();
                    request.setAttribute("listagem", listaExc);

                    rd = request.getRequestDispatcher("produto.jsp");
                    rd.forward(request, response);

                    break;

                case "listagem":

                    List<Produto> lista = dao.listar();
                    request.setAttribute("listagem", lista);
                    request.setAttribute("empresasDisponiveis", new model.dao.EmpresaDao().listar());
                    rd = request.getRequestDispatcher("produto.jsp");
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
