package br.com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Cartao;
import br.com.service.CartaoService;
import br.com.vo.CartaoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cartao")
@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	private CartaoService service;

	@Operation(summary = "inserir cartao")
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody CartaoVO vo) {
		Cartao cartao = service.insert(vo);
		
		return Optional.ofNullable(cartao).isPresent() ? new ResponseEntity<>(CartaoVO.getVO(cartao), HttpStatus.CREATED)
				: new ResponseEntity<>(vo, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Operation(summary = "encontrar por numero cartão")
	@GetMapping("/{numeroCartao}")
	public ResponseEntity<?> recoverySaldoByNumeroCartao(@PathVariable(name = "numeroCartao") Long numeroCartao) {
		return new ResponseEntity<>(this.service.recoverySaldoByNumeroCartao(numeroCartao), HttpStatus.OK);
	}

}
