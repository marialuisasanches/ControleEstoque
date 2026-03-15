/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Pedro
 */

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nome;
    private double preco;
    private int estoque;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    
    public Produto(){}

    public Produto(String nome, double preco, int estoque, Empresa empresa) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    @Override
    public String toString(){
        return id + " | "+ nome + " | "+ preco + " | "+ estoque + " | " + empresa;
    }
}
