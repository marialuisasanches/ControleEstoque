/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Pedro
 */
public class EmpresaSrv extends HttpServlet{
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
                
                try{
                    
                    //implementar depois
                    /*
                    String acao = request.getParameter("acao");
                    
                    String id = request.getParameter("id");
                    String nome = request.getParameter("nome");
                    String cnpj = request.getParameter("cnpj");
                    String telefone = request.getParameter("telefone");
                    */
                    
                    switch(acao){
                        case "inclusao":
                            c = new Empresa(nome, cnpj, telefone);
                            
                            try{
                                dao.incluir(c);
                                rd = request.getRequestDispatcher("index.html");
                                rd.forward(request, response);
                            }catch(Exception e){
                                System.out.println("Erro ===> "+e.getMessage());
                            }
                    }
                    
                } catch(Exception e){
                    System.out.println("Erro ===> "+ e.getMessage());
                }
            }
}
