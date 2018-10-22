package com.oberdan.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.oberdan.livraria.dao.DAO;
import com.oberdan.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = -3342056158071366073L;
	Autor autor = new Autor();
	Integer autorId;
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPelaId() {
		this.autor = new DAO<Autor>(Autor.class).obterPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public String salvar() {
		System.out.println("Salvando autor " + this.autor);
		
		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);			
			this.autor = new Autor();
			return "livro?faces-redirect=true";
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
			this.autor = new Autor();
			return "";
		}		
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void altera(Autor autor) {
		this.autor = autor;
	}
	
	public void remove(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
	}
}
