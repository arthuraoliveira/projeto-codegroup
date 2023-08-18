package br.com.codegroup.projetos.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MembroDto {

	@JsonProperty("nome_pessoa")
	private String nomePessoa;

	@JsonProperty("cargo_pessoa")
	private String cargo_pessoa;
	
	@JsonProperty("funcionario")
	private Boolean funcionario;
	
	
}
