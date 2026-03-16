/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Movimentacao;

/**
 *
 * @author Pedro
 */
public class MovimentacaoDao implements InterfaceDao<Movimentacao> {

    @Override
    public void incluir(Movimentacao entidade) throws Exception {
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
    public void editar(Movimentacao entidade) throws Exception {
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
    public void excluir(Movimentacao entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Movimentacao m1 = em.find(Movimentacao.class, entidade.getId());
            em.remove(m1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Movimentacao pesquisarPorId(int id) throws Exception {
        Movimentacao m1 = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            m1 = em.find(Movimentacao.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return m1;
    }

    @Override
    public List<Movimentacao> listar() throws Exception {
        List<Movimentacao> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Movimentacao p").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return lista;
    }

public List<Movimentacao> pesquisarPorObservacao(String busca) throws Exception {
    EntityManager em = ConnFactory.getEntityManager();
    try {
        return em.createQuery(
            "SELECT m FROM Movimentacao m WHERE m.observacao LIKE :busca",
            Movimentacao.class)
                .setParameter("busca", "%" + busca + "%")
                .getResultList();
    } finally {
        em.close();
    }
}
}
