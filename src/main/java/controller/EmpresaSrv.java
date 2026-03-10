/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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
public class EmpresaSrv extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // corrigir depois
            String acao = request.getParameter("acao");

            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String cnpj = request.getParameter("cnpj");
            String telefone = request.getParameter("telefone");

            InterfaceDao dao = new EmpresaDao();
            RequestDispatcher rd;
            Empresa e1 = null;
            
            // corrigir depois
            switch (acao) {
                case "inclusao":
                    e1 = new Empresa(nome, cnpj, telefone);
                    try {
                        dao.incluir(e1);
                        // corrigir depois
                        rd = request.getRequestDispatcher("index.html");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Erro ===> " + e.getMessage());
                    }
                    break;

                case "edicao":
                    e1 = new Empresa(nome, cnpj, telefone);
                    e1.setId(Integer.parseInt(id));
                    try {
                        dao.editar(e1);
                        List<Empresa> lista = dao.listar();
                        request.setAttribute("lista", lista);
                        // rd = request.getRequestDispatcher(); // implementar
                        //rd.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Erro ===> " + e.getMessage());
                    }
                    break;
                
                case "exclusao":
                    try{
                        e1 = new Empresa(nome, cnpj, telefone);
                        e1.setId(Integer.parseInt(id));
                        dao.excluir(e1);
                        
                        List<Empresa> lista = dao.listar();
                        request.setAttribute("lista", lista);
                        //rd = request.getRequestDispatcher(); // implementar
                        //rd.forward(request, response);
                    }catch (Exception e){
                        System.out.println("Erro ===> "+ e.getMessage());
                    }
                    break;
                    
                case "listagem":
                    try{
                        List<Empresa> lista = dao.listar();
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
