package br.com.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exception.ResourceNotFoundException;
import br.com.model.Cartao;
import br.com.repository.CartaoRepository;
import br.com.vo.CartaoVO;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository repository;

	public Cartao insert(CartaoVO vo) {
		Cartao model = CartaoVO.getModel(vo);
		model.setSaldo(new BigDecimal("500.00"));
		return repository.findByNumeroCartao(Long.valueOf(vo.getNumeroCartao())).isPresent() ? null : save(model);
	}

	public Cartao save(Cartao cartao) {
		return repository.save(cartao);
	}

	public BigDecimal recoverySaldoByNumeroCartao(Long numeroCartao) {
		var cartao = repository.findByNumeroCartao(numeroCartao)
				.orElseThrow(() -> new ResourceNotFoundException("CARTAO_INEXISTENTE"));
		return cartao.getSaldo();
	}

	public Cartao findByNumeroCartao(Long numeroCartao) {
		return repository.findByNumeroCartao(numeroCartao)
				.orElseThrow(() -> new ResourceNotFoundException("CARTAO_INEXISTENTE"));
	}

}
