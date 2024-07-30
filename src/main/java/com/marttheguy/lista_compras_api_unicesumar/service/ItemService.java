package com.marttheguy.lista_compras_api_unicesumar.service;

import java.util.List;

import com.marttheguy.lista_compras_api_unicesumar.entity.Item;
import com.marttheguy.lista_compras_api_unicesumar.exception.ItemNaoEncontradoException;

public interface ItemService {	
 
	// o escopo dos metodos que sao implementados na classe ItemServiceImpl
	// que serao disponiveis para uso no controlador
	
	public List<Item> listarItens();
	public Item buscarItemPorId(Long id) throws ItemNaoEncontradoException;
	public Item adicionarItem(Item item);
	public void removerItemPorId(Long id) throws ItemNaoEncontradoException;
	public Item atualizarItemPorId(Long id, Item itemAtualizado) throws ItemNaoEncontradoException;
	
}
