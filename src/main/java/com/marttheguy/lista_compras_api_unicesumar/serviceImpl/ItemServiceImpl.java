package com.marttheguy.lista_compras_api_unicesumar.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marttheguy.lista_compras_api_unicesumar.entity.Item;
import com.marttheguy.lista_compras_api_unicesumar.exception.ItemNaoEncontradoException;
import com.marttheguy.lista_compras_api_unicesumar.repository.ItemRepository;
import com.marttheguy.lista_compras_api_unicesumar.service.ItemService;

import jakarta.transaction.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

	/**
	 * injecao do repositorio fornecido pelo JpaRepository<br>
	 * utilizado para adicionar as funcionalidades aos metodos abaixo
	 */
	@Autowired
	private ItemRepository repositorio;

	/**
	 * retorna uma lista de itens com todos os itens presentes no repositorio
	 */
	@Override
	public List<Item> listarItens() {
		return repositorio.findAll();
	}

	/**
	 * retorna um item especifico com base no identificador fornecido
	 */
	@Override
	public Item buscarItemPorId(Long id) throws ItemNaoEncontradoException {
		return repositorio.findById(id)
				.orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado com o ID:" + id));
	}

	/**
	 * salva um item no repositorio
	 */
	@Override
	@Transactional
	public Item adicionarItem(Item item) {
		return repositorio.save(item);
	}

	/**
	 * remove um item do repositorio com base no identificador fornecido<br>
	 * é transacional, o que da mais confiabilidade e protecao ao estado do banco de dados
	 */
	@Override
	@Transactional
	public void removerItemPorId(Long id) throws ItemNaoEncontradoException {
		if (!repositorio.existsById(id)) {
			throw new ItemNaoEncontradoException("Item não encontrado com ID:" + id);
		}
		repositorio.deleteById(id);
	}

	/**
	 * atualiza um item do repositorio com base no identificador fornecido e um corpo contendo o item atualizado<br>
	 * é transacional, o que da mais confiabilidade e protecao ao estado do banco de dados
	 */
	@Override
	@Transactional
	public Item atualizarItemPorId(Long id, Item itemAtualizado) throws ItemNaoEncontradoException {
		Item itemExistente = repositorio.findById(id)
				.orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado com ID: " + id));
	
		// Atualiza os campos
		itemExistente.setNome(itemAtualizado.getNome());
		itemExistente.setDescricao(itemAtualizado.getDescricao());
		itemExistente.setPreco(itemAtualizado.getPreco());
		itemExistente.setQuantidade(itemAtualizado.getQuantidade());
		itemExistente.setDisponibilidade(itemAtualizado.getDisponibilidade());
		
		return repositorio.save(itemExistente);
	}
	
}
