package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.service.TransacaoService;
import br.com.vo.TransacaoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Transacao")
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	@Autowired
	private TransacaoService service;

	@Operation(summary = "executar transacao")
	@PostMapping
	public ResponseEntity<?> execute(@RequestBody TransacaoVO vo) {
		service.execute(vo);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
