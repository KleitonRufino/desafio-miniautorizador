package br.com.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.model.Cartao;

@JsonPropertyOrder({ "numeroCartao", "senha" })
public class CartaoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4585212527599491027L;
	@JsonProperty("numeroCartao")
	private String numeroCartao;
	@JsonProperty("senha")
	private String senha;

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao.replaceAll("[^a-zA-Z0-9]", "");
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static Cartao getModel(CartaoVO vo) {
		Cartao model = new Cartao();
		model.setNumeroCartao(Long.valueOf(vo.getNumeroCartao()));
		model.setSenha(vo.getSenha());
		return model;
	}

	public static CartaoVO getVO(Cartao model) {
		CartaoVO vo = new CartaoVO();
		vo.setNumeroCartao(String.valueOf(model.getNumeroCartao()));
		vo.setSenha(model.getSenha());
		return vo;
	}

}
