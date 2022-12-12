package br.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	Optional<Cartao> findByNumeroCartao(Long numeroCartao);

	
}
