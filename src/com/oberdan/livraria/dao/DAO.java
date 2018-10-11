package com.oberdan.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();

		JPAUtil.closeEntityManager(em);
	}

	public void remove(T t) {
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();

		JPAUtil.closeEntityManager(em);
	}

	public void atualiza(T t) {
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();

		JPAUtil.closeEntityManager(em);
	}

	public List<T> listaTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		return lista;
	}

	public T obterPorId(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		T objeto = em.find(classe, id);
		return objeto;
	}

}
