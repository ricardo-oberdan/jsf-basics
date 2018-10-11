package com.oberdan.livraria.bean;

import javax.faces.bean.ManagedBean;

import com.oberdan.livraria.modelo.Livro;

@ManagedBean
public class LivroBean {
	Livro livro = new Livro();

	public Livro getLivro() {
		return livro;
	}
	
	public void salvar() {
		System.out.println("Salvando livro" + this.livro);
	}
}
