package br.com.codegroup.projetos.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CargoDto {
	
	@JsonProperty("id_cargo")
	private Long idCargo;

	@JsonProperty("mome")
	private String nome;
}
