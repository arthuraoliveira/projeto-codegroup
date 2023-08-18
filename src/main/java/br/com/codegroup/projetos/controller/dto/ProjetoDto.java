package br.com.codegroup.projetos.controller.dto;

import java.time.OffsetDateTime;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ProjetoDto {

	@JsonProperty("id_projeto")
	private Long idProjeto;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("data_inicio")
	private OffsetDateTime dataInicio;

	@JsonProperty("data_previsao_termino")
	private OffsetDateTime dataPrevisao;

	@JsonProperty("data_real_termino")
	private OffsetDateTime dataFim;

	@JsonProperty("descricao")
	private String descricao;

	@JsonProperty("status")
	private String status;

	@JsonProperty("orcamento_total")
	private Double orcamento;

	@JsonProperty("risco")
	private String risco;

	@JsonProperty("gerente_responsavel")
	private PessoaDto gerenteResponsavel;
	
	@Transient
	public boolean isPermiteDelete() {
		return this.status != null && !this.status.equalsIgnoreCase("iniciado")
				&& !this.status.equalsIgnoreCase("em andamento") && !this.status.equalsIgnoreCase("encerrado");
	}

}
