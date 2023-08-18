package br.com.codegroup.projetos.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MembroProjetoDto {

	@JsonProperty("id_projeto")
	private Long idProjeto;

	@JsonProperty("id_membro")
	private Long idPessoa;
}
