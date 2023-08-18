package br.com.codegroup.projetos.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class ProjetoResponse extends MessageResponse {

	@JsonProperty("id_projeto")
	private Long idProjeto;
	
	@JsonProperty("nome")
	private String nome;
}
