package br.com.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "numeroCartao", "senha" })
public class TransacaoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4585212527599491027L;
	@JsonProperty("numeroCartao")
	private Long numeroCartao;
	@JsonProperty("senha")
	private String senha;
	@JsonProperty("valor")
	private BigDecimal valor;

	public Long getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
