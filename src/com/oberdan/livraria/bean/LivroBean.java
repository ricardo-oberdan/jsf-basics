package com.oberdan.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.oberdan.livraria.dao.AutorDao;
import com.oberdan.livraria.dao.LivroDao;
import com.oberdan.livraria.interceptador.Log;
import com.oberdan.livraria.interceptador.Transacional;
import com.oberdan.livraria.modelo.Autor;
import com.oberdan.livraria.modelo.Livro;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class LivroBean implements Serializable {

	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	private List<Livro> livros;
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

	@Inject
	private LivroDao livroDao;

	@Inject
	private AutorDao autorDao;
	
	@Inject
	FacesContext facesContext;

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
	
	@Log
	public void carregarLivroPelaId() {
		this.livro = this.livroDao.obterPorId(livroId);
	}

	@Transacional
	public void salvar() {
		System.out.println("Salvando livro" + this.livro);

		if (livro.getAutores().isEmpty()) {
			facesContext.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
			// throw new RuntimeException("Livro deve ter pelo menos um Autor");
		}

		if (this.livro.getId() == null) {
			this.livroDao.adiciona(this.livro);
		} else {
			this.livroDao.atualiza(this.livro);
		}
		this.livros = livroDao.listaTodos();
		this.livro = new Livro();
	}
	
	@Transacional
	public void remover(Livro livro) {
		System.out.println("Removendo livro" + livro);
		this.livroDao.remove(livro);
		this.livros.remove(livro);
	}

	public void removeAutor(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}

	public void gravarAutor() {
		Autor autor = this.autorDao.obterPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro escrito por: " + autor.getNome());
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	@Log
	public List<Livro> getLivros() {
		if (this.livros == null) {
			this.livros = livroDao.listaTodos();
		}
		return livros;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void altera(Livro livro) {
		this.livro = this.livroDao.obterPorId(livro.getId());
	}
}
