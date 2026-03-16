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
import model.dao.EmpresaDao;
import model.dao.InterfaceDao;

/**
 *
 * @author Pedro
 */
@WebServlet("/empresa")
public class EmpresaSrv extends HttpServlet {

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
            String cnpj = request.getParameter("cnpj");
            String telefone = request.getParameter("telefone");

            InterfaceDao dao = new EmpresaDao();
            RequestDispatcher rd;

            Empresa e1 = null;

            switch (acao) {

                case "inclusao":

                    e1 = new Empresa();
                    e1.setNome(nome);
                    e1.setCnpj(cnpj);
                    e1.setTelefone(telefone);

                    dao.incluir(e1);

                    rd = request.getRequestDispatcher("empresa?acao=listagem");
                    rd.forward(request, response);

                    break;

                case "pre-edicao":
                    e1 = (Empresa) dao.pesquisarPorId(Integer.parseInt(id));

                    request.setAttribute("empresaEditando", e1);

                    rd = request.getRequestDispatcher("empresa.jsp");
                    rd.forward(request, response);

                    break;

                case "edicao":
                    e1 = (Empresa) dao.pesquisarPorId(Integer.parseInt(id));

                    e1.setNome(nome);
                    e1.setCnpj(cnpj);
                    e1.setTelefone(telefone);
                    dao.editar(e1);

                    List<Empresa> listaEdit = dao.listar();
                    request.setAttribute("listagem", listaEdit);

                    rd = request.getRequestDispatcher("empresa.jsp");
                    rd.forward(request, response);
                    break;

                case "exclusao":

                    e1 = new Empresa();

                    if (id != null) {
                        e1.setId(Integer.parseInt(id));
                        dao.excluir(e1);
                    }

                    List<Empresa> listaExc = dao.listar();
                    request.setAttribute("listagem", listaExc);

                    rd = request.getRequestDispatcher("empresa.jsp");
                    rd.forward(request, response);

                    break;

                case "listagem":

                    List<Empresa> lista = dao.listar();
                    request.setAttribute("listagem", lista);

                    rd = request.getRequestDispatcher("empresa.jsp");
                    rd.forward(request, response);

                    break;

                case "pesquisa":
                    String nomeBusca = request.getParameter("busca");

                    model.dao.EmpresaDao eDao = new model.dao.EmpresaDao();
                    List<Empresa> resultado = eDao.pesquisarPorNome(nomeBusca);

                    request.setAttribute("listagem", resultado);
                    rd = request.getRequestDispatcher("empresa.jsp");
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
