package com.marttheguy.lista_compras_api_unicesumar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marttheguy.lista_compras_api_unicesumar.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

	//	contem os metodos de repositorio basico padrao fornecido pelo JpaRepository
	//	pode adicionar novos escopos de metodos personalizados aqui
	//	
	//	no momento todos esses metodos retornam uma lista de itens que contenham
	//	o mesmo valor de atributo (especificado no parametro)
	//	 
	//	exemplo: findByPreco poderia retornar todos os itens que custam 5.99 	
	
    List<Item> findByNome(String nome);
    List<Item> findByDisponibilidade(boolean disponibilidade);
    List<Item> findByPreco(Double preco);
    List<Item> findByDescricao(String descricao);
    List<Item> findByQuantidade(Integer quantidade);
	
}
