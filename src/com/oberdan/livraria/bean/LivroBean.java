package com.oberdan.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.oberdan.livraria.dao.DAO;
import com.oberdan.livraria.modelo.Autor;
import com.oberdan.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = -4246504067200584643L;
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;

	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Integer getLivroId() {
		return livroId;
	}
	
	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	
	public void carregarLivroPelaId() {
		this.livro = new DAO<Livro>(Livro.class).obterPorId(livroId);
	}

	public void salvar() {
		System.out.println("Salvando livro" + this.livro);

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
			//throw new RuntimeException("Livro deve ter pelo menos um Autor");
		}

		if(this.livro.getId() == null) {	
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		} else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		
		this.livro = new Livro();
	}
	
	public void remover(Livro livro) {
		System.out.println("Removendo livro" + livro);
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void removeAutor(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).obterPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro escrito por: " + autor.getNome());
	}
	
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}
