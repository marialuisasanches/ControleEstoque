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
import java.util.Date;
import java.util.List;
import model.Movimentacao;
import model.dao.InterfaceDao;
import model.dao.MovimentacaoDao;


/**
 *
 * @author Pedro
 */
public class MovimentacaoSrv {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // corrigir depois
            String acao = request.getParameter("acao");
            String id = request.getParameter("id");
            String qtd = request.getParameter("qtd");
            String data = request.getParameter("data");
            String observacao = request.getParameter("observacao");
            String produto = request.getParameter("produtoMovimento");
            String tipo = request.getParameter("tipo");

            InterfaceDao dao = new MovimentacaoDao();
            RequestDispatcher rd;
            Movimentacao m1 = null;
            
            // corrigir depois
            switch (acao) {
                case "inclusao":
                    //m1 = new Movimentacao(qtd, data, observacao, produto, tipo);
                    try {
                        dao.incluir(m1);
                        // corrigir depois
                        rd = request.getRequestDispatcher("index.html");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Erro ===> " + e.getMessage());
                    }
                    break;

                case "edicao":
                    //m1 = new Movimentacao(qtd, data, observacao, produto, tipo);
                    m1.setId(Integer.parseInt(id));
                    try {
                        dao.editar(m1);
                        List<Movimentacao> lista = dao.listar();
                        request.setAttribute("lista", lista);
                        // rd = request.getRequestDispatcher(); // implementar
                        //rd.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Erro ===> " + e.getMessage());
                    }
                    break;
                
                case "exclusao":
                    try{
                        //m1 = new Movimentacao(qtd, data, observacao, produto, tipo);
                        m1.setId(Integer.parseInt(id));
                        dao.excluir(m1);
                        
                        List<Movimentacao> lista = dao.listar();
                        request.setAttribute("lista", lista);
                        //rd = request.getRequestDispatcher(); // implementar
                        //rd.forward(request, response);
                    }catch (Exception e){
                        System.out.println("Erro ===> "+ e.getMessage());
                    }
                    break;
                    
                case "listagem":
                    try{
                        List<Movimentacao> lista = dao.listar();
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
