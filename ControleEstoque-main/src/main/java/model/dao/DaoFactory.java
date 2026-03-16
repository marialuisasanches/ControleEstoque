/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author Pedro
 */
public class DaoFactory {
    public static EmpresaDao novaEmpresaDao() throws Exception{
        return new EmpresaDao();
    }
    
    public static ProdutoDao novoProdutoDao() throws Exception{
        return new ProdutoDao();
    }
    
    public static MovimentacaoDao novaMovimentacao() throws Exception{
        return new MovimentacaoDao();
    }
}
