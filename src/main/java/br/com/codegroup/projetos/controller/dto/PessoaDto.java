package br.com.codegroup.projetos.controller.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PessoaDto {

	@JsonProperty("id_pessoa")
	private Long idPessoa;

	@JsonProperty("mome")
	private String nome;

	@JsonProperty("data_nascimento")
	private OffsetDateTime dataNascimento;

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("funcionario")
	private boolean funcionario;

	@JsonProperty("cargo")
	private CargoDto cargo;

}
