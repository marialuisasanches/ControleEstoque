/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Pedro
 */
public class Movimentacao {
    private int id;
    private int qtd;
    private Date data;
    private String observacao;
    private Produto produtoMovimento;
    private TipoMovimentacao tipo;
    
    public Movimentacao(){}

    public Movimentacao(int qtd, Date data, String observacao, Produto produtoMovimento, TipoMovimentacao tipo) {
        this.qtd = qtd;
        this.data = data;
        this.observacao = observacao;
        this.produtoMovimento = produtoMovimento;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Produto getProdutoMovimento() {
        return produtoMovimento;
    }

    public void setProdutoMovimento(Produto produtoMovimento) {
        this.produtoMovimento = produtoMovimento;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return id + " | " + qtd + " | "+ data + " | " + observacao + " | " + produtoMovimento + " | " + tipo;
    }
}
