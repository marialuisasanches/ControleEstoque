/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Produto;
import model.dao.InterfaceDao;
import model.dao.ProdutoDao;

/**
 *
 * @author Pedro
 */
public class ProdutoSrv {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // corrigir depois
            String acao = request.getParameter("acao");

            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String preco = request.getParameter("preco");
            String estoque = request.getParameter("estoque");
            String empresa = request.getParameter("empresa");

            InterfaceDao dao = new ProdutoDao();
            RequestDispatcher rd;
            Produto p1 = null;
            
            // corrigir depois
            switch (acao) {
                case "inclusao":
                    //p1 = new Produto(nome, preco, estoque, empresa);
                    try {
                        dao.incluir(p1);
                        // corrigir depois
                        rd = request.getRequestDispatcher("index.html");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Erro ===> " + e.getMessage());
                    }
                    break;

                case "edicao":
                    //p1 = new Produto(nome, preco, estoque, empresa);
                    p1.setId(Integer.parseInt(id));
                    try {
                        dao.editar(p1);
                        List<Produto> lista = dao.listar();
                        request.setAttribute("lista", lista);
                        // rd = request.getRequestDispatcher(); // implementar
                        //rd.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Erro ===> " + e.getMessage());
                    }
                    break;
                
                case "exclusao":
                    try{
                        //p1 = new Produto(nome, preco, estoque, empresa);
                        p1.setId(Integer.parseInt(id));
                        dao.excluir(p1);
                        
                        List<Produto> lista = dao.listar();
                        request.setAttribute("lista", lista);
                        //rd = request.getRequestDispatcher(); // implementar
                        //rd.forward(request, response);
                    }catch (Exception e){
                        System.out.println("Erro ===> "+ e.getMessage());
                    }
                    break;
                    
                case "listagem":
                    try{
                        List<Produto> lista = dao.listar();
                        request.setAttribute("listagem", lista);
                        //rd = request.getRequestDispatcher(); // implememtar
                        //rd.forward(request, response);
                    }catch (Exception e){
                        System.out.println("Erro ===> "+ e.getMessage());
                    }
                    break;
                    
                default:
                    System.out.println("Erro na variável acao. "+ acao);
                    break;
            }

        } catch (Exception e) {
            System.out.println("Erro ===> " + e.getMessage());
        }
    }
}
