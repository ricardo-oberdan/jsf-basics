package com.oberdan.livraria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import com.oberdan.livraria.dao.DAO;
import com.oberdan.livraria.modelo.Autor;

@ManagedBean
public class AutorBean implements Serializable {

	private static final long serialVersionUID = -3342056158071366073L;
	Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public String salvar() {
		System.out.println("Salvando autor " + this.autor);
		new DAO<Autor>(Autor.class).adiciona(this.autor);
		this.autor = new Autor();
		return "livro?faces-redirect=true";
	}
}
