package br.com.codegroup.projetos.controller.response;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MembroResponse extends MessageResponse {

	@JsonProperty("id_membro")
	private Long idMembro;
}
