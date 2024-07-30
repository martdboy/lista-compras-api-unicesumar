package com.marttheguy.lista_compras_api_unicesumar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * e tratado como uma entidade<br>
 * mapeia uma tabela chamada "itens"
 */
@Entity
@Table(name="itens")
public class Item {
	
	/**
	 * identificador do item<br>
	 * gerado de forma automatica<br>
	 * mapeia coluna "id"
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/**
	 * nome do item<br>
	 * mapeia coluna "nome"
	 */
	@Column(name="nome")
	private String nome;
	
	/**
	 * descricao do item<br>
	 * mapeia coluna "descricao"
	 */
	@Column(name="descricao")
	private String descricao;
	
	/**
	 * preco do item<br>
	 * mapeia coluna "preco"
	 */
	@Column(name="preco")
	private Double preco;
	
	/**
	 * quantidade desse item disponivel no estoque<br>
	 * mapeia coluna "qtd"
	 */
	@Column(name="qtd")
	private Integer quantidade;

	/**
	 * disponibilidade para compra do produto (true - t, false - f)<br>
	 * mapeia coluna "disponibilidade"
	 */
	@Column(name="disponibilidade")
	private boolean disponibilidade;
	
	
	// construtores
	public Item() {};
	
	/**
	 * inicializa uma instancia de item com os parametros abaixo
	 * @param Long id
	 * @param String nome
	 * @param String descricao
	 * @param Double preco
	 * @param Integer quantidade
	 * @param boolean disponibilidade
	 */
	public Item(Long id, String nome, String descricao, Double preco, Integer quantidade, boolean disponibilidade) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.disponibilidade = disponibilidade;
		
	}
	
	
	// getters
	public long getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public Double getPreco() {
		return this.preco;
	}
	
	public Integer getQuantidade() {
		return this.quantidade;
	}
	
	public boolean getDisponibilidade() {
		return this.disponibilidade;
	}
	
	// setters
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	// tostring
	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", preco=" + preco +
				", quantidade=" + quantidade +
				", disponibilidade=" + disponibilidade +
				'}';
	}
}