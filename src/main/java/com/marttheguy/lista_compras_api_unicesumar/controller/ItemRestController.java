package com.marttheguy.lista_compras_api_unicesumar.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marttheguy.lista_compras_api_unicesumar.entity.Item;
import com.marttheguy.lista_compras_api_unicesumar.exception.ItemNaoEncontradoException;
import com.marttheguy.lista_compras_api_unicesumar.service.ItemService;

@RestController
@RequestMapping("api/itens")
@CrossOrigin(origins = "http://localhost:5500")
public class ItemRestController {
	
	/**
	 * injecao do servico para utilizacao dos metodos fornecidos pela classe ItemServiceImpl
	 */
	@Autowired
	private ItemService service; 
	
	/**
	 * escuta por requisicoes GET para a rota "/api/itens/", utiliza o servico para 
	 * listar os itens, retorna uma respsota de cabecalho "404 NOT FOUND" caso nenhum 
	 * item seja encontrado e uma resposta "200 OK" para caso tenha encontrado
	 * um ou mais itens
	 *
	 * @return um status HTTP (200 ou 404) juntamente com um corpo contendo todos os itens encontrados (caso houver)
	 */
	@GetMapping("/")
	public ResponseEntity<List<Item>> listarItens() {
		List<Item> listaProdutos = service.listarItens();
		if (listaProdutos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listaProdutos);
	}
	
	/**
	 * escuta por requisicoes GET na rota "/api/itens/id/", utiliza o servico para buscar por um 
	 * item especifico a partir do seu id, retorna "200 OK" caso encontre e "404 NOT FOUND"
	 * caso nao encontre
	 * 
	 * @param Long id
	 * @return um status HTTP (200 ou 404) juntamente com um corpo contendo o item buscado (caso econtrado)
	 * @throws ItemNaoEncontradoException
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<Item> buscarItem(@PathVariable Long id) throws ItemNaoEncontradoException {
		try {
			Item item = service.buscarItemPorId(id);
			return ResponseEntity.ok(item);
		} catch (ItemNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * escuta por requisicoes POST na rota "/api/itens/", utiliza o servico para adicionar
	 * um novo item utilizando o corpo do novo item passado na requisicao, retorna
	 * "201 CREATED" caso adicione com sucesso e "500 INTERNAL SERVER ERROR" caso contrario
	 * 
	 * @param Item novoItem
	 * @return um status HTTP (201 ou 500) juntamente com um corpo contendo o novo item (no caso de adicao com sucesso)
	 */
	@PostMapping("/")
	public ResponseEntity<Item> adicionarItem(@RequestBody Item novoItem) {	
		try {
			Item item = service.adicionarItem(novoItem);
			
			return ResponseEntity
					.created(URI.create("/id/" + item.getId()))
					.body(item);			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * escuta por requisicoes DELETE na rota "/api/itens/id/", utiliza o servico para remover
	 * o item pelo seu id, caso seja removido retorna com um "204 NO CONTENT", caso contrario
	 * retorna com um "404 NOT FOUND"
	 * 
	 * @param Long id
	 * @return um status HTTP (204 ou 404)
	 */
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> removerItem(@PathVariable Long id) {
		try {
			service.removerItemPorId(id);
			return ResponseEntity.noContent().build();
		} catch (ItemNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * escuta por requisicoes PUT na rota "/api/itens/id/", utiliza o servico para atualizar o item
	 * utilizando tanto o id do mesmo, como o proprio item atualizado passado no corpo da requisicao,
	 * caso seja atualizado retornara com status "200 OK" caso contrario retorna com "404 NOT FOUND"
	 * 
	 * @param Long id
	 * @param Item itemAtualizado
	 * @return um status HTTP (200 ou 404) juntamente de um corpo contendo o Item (caso houver)
	 */
	@PutMapping("/id/{id}")
	public ResponseEntity<Item> atualizarItem(@PathVariable Long id, @RequestBody Item itemAtualizado) {
		try {
			Item item = service.atualizarItemPorId(id, itemAtualizado);
			return ResponseEntity.ok(item);
		} catch (ItemNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
	}
	

}
