package com.oberdan.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager manager, Class<T> classe) {
		this.classe = classe;
		this.em = manager;
	}

	public void adiciona(T t) {
		em.persist(t);
	}

	public void remove(T t) {
		Object a = em.merge(t);
		em.remove(a);
	}

	public void atualiza(T t) {
		em.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		return lista;
	}

	public T obterPorId(Integer id) {
		T objeto = em.find(classe, id);
		return objeto;
	}

}
