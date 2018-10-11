package com.oberdan.livraria.bean;

import javax.faces.bean.ManagedBean;

import com.oberdan.livraria.dao.DAO;
import com.oberdan.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {
	Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public void salvar() {
		System.out.println("Salvando autor " + this.autor);
		new DAO<Autor>(Autor.class).adiciona(this.autor);
		this.autor = new Autor();
	}
}
