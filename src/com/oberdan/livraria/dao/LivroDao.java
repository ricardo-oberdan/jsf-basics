package com.oberdan.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.oberdan.livraria.modelo.Livro;

@SuppressWarnings("serial")
public class LivroDao implements Serializable {

	@Inject
	private EntityManager em;

	private DAO<Livro> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Livro>(em, Livro.class);
	}

	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public void atualiza(Livro t) {
		dao.atualiza(t);
	}

	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}

	public Livro obterPorId(Integer id) {
		return dao.obterPorId(id);
	}

}
