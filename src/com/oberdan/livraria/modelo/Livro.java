package com.oberdan.livraria.modelo;

public class Livro {

	private String titulo;
	private String isbn;
	private Double preco;
	private String dataLancamento;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	@Override
	public String toString() {
		return "Livro [titulo=" + titulo + ", isbn=" + isbn + ", preco=" + preco + ", dataLancamento=" + dataLancamento
				+ "]";
	}

}
