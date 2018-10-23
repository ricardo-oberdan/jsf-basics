package com.oberdan.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.oberdan.livraria.modelo.Autor;

@SuppressWarnings("serial")
public class AutorDao implements Serializable{

	@Inject
	private EntityManager em;

	private DAO<Autor> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Autor>(em, Autor.class);
	}

	public void adiciona(Autor t) {
		dao.adiciona(t);
	}

	public void remove(Autor t) {
		dao.remove(t);
	}

	public void atualiza(Autor t) {
		dao.atualiza(t);
	}

	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

	public Autor obterPorId(Integer id) {
		return dao.obterPorId(id);
	}

}
