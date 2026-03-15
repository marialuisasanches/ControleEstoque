/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Produto;

/**
 *
 * @author Pedro
 */
public class ProdutoDao implements InterfaceDao<Produto> {

    @Override
    public void incluir(Produto entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void editar(Produto entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Produto entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Produto p1 = em.find(Produto.class, entidade.getId());
            em.remove(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Produto pesquisarPorId(int id) throws Exception {
        Produto p1 = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            p1 = em.find(Produto.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p1;
    }

    @Override
    public List<Produto> listar() throws Exception {
        List<Produto> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Produto p").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return lista;
    }

    public List<Produto> pesquisarPorEmpresa(String nomeEmpresa) {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produto p WHERE p.empresa.nome LIKE :n", Produto.class)
                    .setParameter("n", "%" + nomeEmpresa + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
