/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Empresa;
import static model.dao.ConnFactory.getEntityManager;

/**
 *
 * @author Pedro
 */
public class EmpresaDao implements InterfaceDao<Empresa> {

    @Override
    public void incluir(Empresa entidade) throws Exception {
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
    public void editar(Empresa entidade) throws Exception {
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
    public void excluir(Empresa entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Empresa p1 = em.find(Empresa.class, entidade.getId());
            em.remove(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Empresa pesquisarPorId(int id) throws Exception {
        Empresa p1 = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            p1 = em.find(Empresa.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p1;
    }

    @Override
    public List<Empresa> listar() throws Exception {
        List<Empresa> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Empresa p").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return lista;
    }
public List<Empresa> pesquisarPorNome(String nome) throws Exception {
    EntityManager em = ConnFactory.getEntityManager();
    try {
        return em.createQuery(
            "SELECT e FROM Empresa e WHERE e.nome LIKE :nome",
            Empresa.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    } finally {
        em.close();
    }
}
}
