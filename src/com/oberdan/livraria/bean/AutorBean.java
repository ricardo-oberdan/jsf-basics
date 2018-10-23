package com.oberdan.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.oberdan.livraria.dao.AutorDao;
import com.oberdan.livraria.interceptador.Log;
import com.oberdan.livraria.interceptador.Transacional;
import com.oberdan.livraria.modelo.Autor;

@SuppressWarnings("serial")
@Named
@ViewScoped //javax.faces.view.ViewScoped
public class AutorBean implements Serializable {

	Autor autor = new Autor();
	Integer autorId;
	
	@Inject
	private AutorDao dao;
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	@Log
	public void carregarAutorPelaId() {
		this.autor = this.dao.obterPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	@Transacional
	public String salvar() {
		System.out.println("Salvando autor " + this.autor);
		
		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);			
			this.autor = new Autor();
			return "livro?faces-redirect=true";
		} else {
			this.dao.atualiza(this.autor);
			this.autor = new Autor();
			return "";
		}		
	}
	
	@Log
	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}
	
	public void altera(Autor autor) {
		this.autor = autor;
	}
	
	@Transacional
	public void remove(Autor autor) {
		this.dao.remove(autor);
	}
}
