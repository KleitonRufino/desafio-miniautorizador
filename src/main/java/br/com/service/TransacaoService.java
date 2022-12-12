package br.com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.exception.ConditionsFaildedException;
import br.com.model.Cartao;
import br.com.vo.TransacaoVO;

@Service
public class TransacaoService {

	@Autowired
	private CartaoService cartaoService;

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation=Propagation.REQUIRED,readOnly=false)
	public void execute(TransacaoVO vo) {
		Cartao cartao = this.cartaoService.findByNumeroCartao(vo.getNumeroCartao());
		Optional<Cartao> cartaoOp = Optional.ofNullable(cartao);
		cartao = cartaoOp.stream().filter(c -> c.getSenha().equalsIgnoreCase(vo.getSenha())).findFirst()
				.orElseThrow(() -> new ConditionsFaildedException("SENHA_INVALIDA"));
		cartaoOp = Optional.ofNullable(cartao);
		cartao = cartaoOp.stream()
				.filter(c -> (c.getSaldo().compareTo(vo.getValor()) == 1 || c.getSaldo().compareTo(vo.getValor()) == 0))
				.findFirst().orElseThrow(() -> new ConditionsFaildedException("SALDO_INSUFICIENTE"));

		cartao.setSaldo(cartao.getSaldo().subtract(vo.getValor()));
		cartaoService.save(cartao);
	}

}
